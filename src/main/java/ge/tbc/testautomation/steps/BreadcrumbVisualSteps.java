package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.FinancePage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BreadcrumbVisualSteps {
    private final Page page;
    private final FinancePage financePage;

    public BreadcrumbVisualSteps(Page page) {
        this.page = page;
        this.financePage = new FinancePage(page);
    }

    public BreadcrumbVisualSteps stabilize() {
        page.addStyleTag(new Page.AddStyleTagOptions().setContent(
                "*{animation:none!important;transition:none!important} html,body{scroll-behavior:auto!important}"
        ));
        return this;
    }

    public BreadcrumbVisualSteps assertBreadcrumbMatchesBaseline() {
        return assertBreadcrumbMatchesBaseline("breadcrumb_business_loans_ka.png");
    }

    public BreadcrumbVisualSteps assertBreadcrumbMatchesBaseline(String baselineFileName) {
        Locator crumb = financePage.breadCrumbContainer;
        assertThat(crumb).isVisible();

        Path baseline = Paths.get("__snapshots__/visual/" + baselineFileName);
        Path actual   = Paths.get("target/visual/actual/" + baselineFileName);
        Path diffOut  = Paths.get("target/visual/diff/" + baselineFileName.replace(".png", "_diff.png"));

        try {
            byte[] currentPng = crumb.screenshot(new Locator.ScreenshotOptions());
            Files.createDirectories(actual.getParent());
            Files.write(actual, currentPng);

            if (!Files.exists(baseline)) {
                Files.createDirectories(baseline.getParent());
                Files.write(baseline, currentPng);
                return this;
            }

            BufferedImage base = ImageIO.read(baseline.toFile());
            BufferedImage curr = ImageIO.read(new ByteArrayInputStream(currentPng));
            double diffRatio = pixelDiffRatio(base, curr, diffOut);

            if (diffRatio > 0.003) {
                throw new AssertionError(String.format(
                        "Visual diff too high: %.3f%% (baseline=%s, actual=%s, diff=%s)",
                        diffRatio * 100.0, baseline, actual, diffOut
                ));
            }
            return this;

        } catch (IOException e) {
            throw new RuntimeException("Visual comparison failed due to IO error", e);
        }
    }

    private static double pixelDiffRatio(BufferedImage a, BufferedImage b, Path diffOut) throws IOException {
        int w = a.getWidth();
        int h = a.getHeight();
        if (w != b.getWidth() || h != b.getHeight()) {
            Files.createDirectories(diffOut.getParent());
            ImageIO.write(b, "png", diffOut.toFile());
            return 1.0;
        }

        int total = w * h;
        int diffCount = 0;
        BufferedImage diff = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int p1 = a.getRGB(x, y);
                int p2 = b.getRGB(x, y);
                if (p1 != p2) {
                    diffCount++;
                    diff.setRGB(x, y, 0xFF0000);
                } else {
                    diff.setRGB(x, y, 0xFFFFFF);
                }
            }
        }

        Files.createDirectories(diffOut.getParent());
        ImageIO.write(diff, "png", diffOut.toFile());

        return (double) diffCount / (double) total;
    }
}

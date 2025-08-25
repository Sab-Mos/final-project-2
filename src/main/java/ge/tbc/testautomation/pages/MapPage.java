package ge.tbc.testautomation.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class MapPage {

    public SelenideElement
            marker = $$("gmp-advanced-marker" +
            ".yNHHyP-marker-view[role='button']").findBy(Condition.visible),

    allFilterBtn = $(".tbcx-pw-tab-menu .tbcx-pw-tab-menu__item.active"),

    branchesFilterBtn = $x("//span[text()='ფილიალები']/parent::button"),

    atmFilterBtn = $x("//span[text()='ბანკომატები']/parent::button"),

    listContainer = $x("//div[contains(@class, " +
            "'tbcx-pw-atm-branches-section__list-wrapper')]"),

    dropDown = $(".tbcx-dropdown-selector"),

    openTwentyFourHoursFilter = $x("(//label[@class='tbcx-pw-chip'])[1]"),

    map = $(".gm-style").shouldBe(Condition.visible);


    public ElementsCollection

            options = $$("tbcx-dropdown-popover-item " +
            ".tbcx-dropdown-popover-item__title"),

    listItems = $$("app-atm-branches-section-list-item");


    public SelenideElement getItemLabel(SelenideElement item) {
        return item.$x(".//div[contains(@class,'tbcx-pw-atm-branches-section__list-item-label')]");
    }

}

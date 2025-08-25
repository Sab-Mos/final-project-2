package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FinancePage {

    public SelenideElement
            breadCrumbContainer = $("ul.tbcx-pw-breadcrumbs__items");

    public ElementsCollection

    breadcrumbLinks =
            $$("ul.tbcx-pw-breadcrumbs__items > li.tbcx-pw-breadcrumbs__item a");
}

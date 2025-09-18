package ge.tbc.testautomation.data;

import org.testng.annotations.DataProvider;

public class FilterDataProvider {

    @DataProvider(name="categoryFiltersAndUrlTokens")
    public Object[][] filterData(){
        return new Object[][]{
                {"მოგზაურობა", "Travel"},
                {"შოპინგი", "Shopping"},
                {"კაფე და რესტორანი", "CafeandRestaurant"},
        };
    }
}

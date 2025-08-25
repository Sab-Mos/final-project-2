# TBC Bank UI Automation — Final Project

This project contains automated UI tests for [TBC Bank](https://tbcbank.ge/ka) using **Java, Selenide, and TestNG**.  
The goal is to validate key user journeys across desktop and mobile layouts.

---

## Project Structure

```
ge.tbc.testautomation
│
├── data
│   └── Constants.java              # URLs, test data, device parameters
│
├── pages
│   ├── CommonPage.java             # Page elements (search, map, etc.)
│   ├── MapPage.java
│   └── HomePage.java
│
├── steps
│   ├── CommonSteps.java            # Reusable actions (open page, search, accept cookies)
│   └── MapSteps.java
│
├── runners
│   └── BaseTest.java               # Test setup, device handling (desktop/mobile)
│
└── tests
    ├── SearchResultsTest.java
    ├── MapTest.java
    ├── TabsFiltersTest.java
    ├── GlobalNavigationTest.java
    └── ExchangeTest.java
```

---

## Setup

### Prerequisites
- Java 17+
- Maven
- IntelliJ IDEA / VS Code
- Chrome browser

### Installation
Clone the repository and install dependencies:
```bash
git clone <repo-url>
cd <repo-folder>
```

### Run Tests
Run both desktop and mobile suites in parallel via TestNG XML:
```
```

`testng.xml` defines two test suites:
- **Desktop Suite** → runs with `browserSize=1440x900`
- **Mobile Suite** → runs with `browserSize=375x667`

Device is controlled via the `isMobile` flag in `BaseTest` which is passed 
down to corresponding tests which extend it.

---

## Scenarios Automated

### 1. Map Zoom and Pan — Locations Page
- Verify that the map container loads successfully
- Ensure at least one marker  is visible on the map
- Perform zoom in on the map 
- Perform zoom out on the map 
- Perform pan action on the map (drag in any direction) and verify that the map content shifts accordingly

### 2. Tabs & Filters Functionality (ATMs, Branches, 24/7, Open Now)
- Switch between ATMs and Branches tabs
- Apply and remove filters (24/7)
- Verify results list updates accordingly

### 3. Search — Results and Empty State Validation
- Perform search with valid keyword → results displayed
- Perform search with invalid keyword → empty state displayed
- Close search overlay → page returns to normal state

### 4. Global Navigation — Mega Menu & Breadcrumbs
- Hover on main navigation
- Open mega menu, select a category (e.g., Business Loans)
- Verify page URL and breadcrumb trail
- Confirm last breadcrumb matches the current page
- *(Desktop only: breadcrumbs are hidden on mobile layout so presence of a 
  single breadcrumb item is validated on mobile resolution)*

### 5. Currency Exchange & Converter
- Navigate to Currency section
- Verify rates table shows USD, EUR, GBP with Buy/Sell columns
- Enter value into converter → conversion calculated
- Swap currencies → input currencies update accordingly

---

## Key Features

- **Cross-device testing**: Desktop & Mobile handled in one project (`isMobile` flag in `BaseTest`)
- **Reusable Steps/PageObjects**: Clear separation of concerns between elements and actions
- **Parallel Execution**: Desktop + Mobile tests run simultaneously via TestNG
- **Zephyr Integration**: All scenarios documented in Zephyr (linked to Jira test cases)

---

## Configuration
Key configuration files:
- `testng.xml` - Test suite configuration and parallel execution
- `BaseTest.java` - Browser settings, timeouts, and device parameters
- `Constants.java` - Test URLs and environment data
## Troubleshooting
Common issues and solutions:
- **Chrome driver issues**: Ensure Chrome browser is updated
- **Element not found**: Check if page loads completely before interaction
- **Mobile tests failing**: Verify viewport settings in BaseTest


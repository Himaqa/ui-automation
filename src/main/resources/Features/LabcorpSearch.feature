Feature: feature to test Lab corp careers search functionality

  @Smoke
  Scenario Outline: Validate Lab corp career search is working

    Given Open LabCorp website
    When User clicks LabCorp careers link and go to careers page
    And User enters search text in careers search box
    And User selects and browse the position from searched results
    Then Validate Job title
    Then Validate Job Id
    Then Validate Job Location
    Then Validate first sentence of Job Description
    Then Validate second bullet point under Management Support Description
    Then Validate third Requirement Description
    When User clicks Apply Now
    And Enter <username> and <password> to Login to WorkDay site
    Then Validate job title in workday/apply page
    Then User returns to Careers Job search page

    Examples:
    |username|password|
    |himaja.qaanalyst@gmail.com|Labcorp@25|
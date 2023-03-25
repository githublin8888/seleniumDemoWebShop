Feature: Register

  Scenario Outline: Unregistered user
    Given I have ticked the "<Gender>"
    And I have entered my "<First name>"
    And I also have entered "<Last name>"
    And I have typed in "<Email>"
    And I enter a "<Password>"
    And I confirm "<Password>"
    When I click the Register "<Button>"
    Then My registration will be "<result>"

    Examples:
      | Gender | First name | Last name | Email               | Password | Password | Button | result    |
      | Male   | mengj      | Zhouj     | mengj@hotmail.com   | zx123456 | zx123456 | Yes    | completed |
      | Male   | Chunlin    | Zhou      | chunlin@hotmail.com | zxc123   | zxc123   | Yes    | rejected  |
      #| Male   | Chunl      | Zhou      | chunli@hotmail.com  | zx12345  | zx12345  | Yes    | rejected  |

  Scenario Outline: uncompleted username
    Given I have ticked the "<Gender>"
    And I have entered my "<First name>"
    And I also have entered "<Last name>"
    And I have typed in "<Email>"
    And I enter a "<Password>"
    And I confirm "<Password>"
    When I click the Register "<Button>"
    Then I will not be "<register>"

    Examples:
      | Gender | First name | Last name | Email             | Password | Password | Button | register    |
      | Male   |            | meng      | mengj@hotmail.com | zx123456 | zx123456 | Yes    | noFirstName |
      | Female | jing       |           | mengj@hotmail.com | zx123456 | zx123456 | Yes    | noLastName  |
# Scenario Quality Checker
## For analysts documenting functional requirements with scenarios, our SQC application will provide quantitative information and enable detection of problems in functional requirements written in the form of scenarios. The application will be available via GUI and also as a remote API, thanks to which it can be integrated with existing tools.
The program operates on scenarion in following format:
- The scenario includes a header specifying its title and actors (external and system)
- The scenario consists of steps (each step contains text)
- Steps can contain sub-scenarios (any level of nesting)
- The steps may start with the keywords IF, ELSE, FOR EACH

Example:

Title: Book addition

Actors:  Librarian

System actor: System

- Librarian selects options to add a new book item
- A form is displayed.
- Librarian provides the details of the book.
- IF: Librarian wishes to add copies of the book
    - Librarian chooses to define instances
    - System presents defined instances
    - FOR EACH: instance:
        - Librarian chooses to add an instance
        - System prompts to enter the instance details
        - Librarian enters the instance details and confirms them.
        - System informs about the correct addition of an instance and presents the updated list of instances.
- Librarian confirms book addition.
- System informs about the correct addition of the book.

### functionality
- Check the number of the steps the whole scenario contains, to asses how complicated it is.
- Check how many steps contain keywords to find out how many conditional decisions are in the scenario.
- Obtain a scenario containing only sub-scenarios up to a certain level to present a simplified version of the requirements.
- Using the available functions via REST to be able to integrate the tool with other applications.

### important links
- [link to sprint backlog - excel](https://docs.google.com/spreadsheets/d/1VTPMHFyXUM11LUr3ILr5nuh_HHOfM-2V/edit?usp=sharing&ouid=107569523661238886492&rtpof=true&sd=true)
- [link to sprint backlog - trello](https://trello.com/b/Ag2yoZ3L/scrum-board)
- [link to automatically generated documentation](https://mateuszn949.github.io/ScenarioQualityChecker/javadoc/)

### Example of Scenario in JSON format;
```json
{
  "title": "title",
  "actors": [
    "actor1",
    "actor2"
  ],
  "systemActor": "systemActor",
  "steps": [
    {
      "keyword": "IF",
      "text": "text",
      "steps": null
    },
    {
      "text": "text",
      "steps": [
        {
          "text": "text",
          "steps": null
        }
      ]
    }
  ]
}
```

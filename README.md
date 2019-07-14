# website-screenshot-utility
This project is a small utility to take screenshots of a website. It is written in Java and creates the screenshots using Firefox and Selenium.

## Getting Started
### Prerequisites
You need the following tools to assemble this project:
* [Java] (https://www.java.com/) - Programming Language and Runtime Environment
* [Maven](https://maven.apache.org/) - Dependency Management

You need the following tools to run this project:
* [Java] (https://www.java.com/) - Programming Language and Runtime Environment
* [Firefox](https://www.mozilla.org/en-GB/firefox/) - Browser and Execution Environment
* [Gecko Driver] (https://github.com/mozilla/geckodriver) - Driver to connect Java and Firefox 

### Installing
The assembly of this project is done with Maven. The goals I use to achieve this are "clean compile assembly:single". This will create a runnable jar-file in the target folder.

### Running
To run the jar-file, it is necessary to have a version of the gecko driver for your OS (see https://github.com/mozilla/geckodriver for more information). It must be located in the folder "drivers" next to the jar-file. The name of the driver must be geckodriver.current. This results in the following folder structure:
|-- website-screenshot-utility-...
|-- drivers
|   |-- geckodriver.current

## Paramter
This tool uses the following parameter (in this order):
* 1: URL of the website
* 2: Width of the resulting screenshot
* 3: Height of the resulting screenshot
* 4: Name of the screenshot-file (absolute or relative)
* 5: Time to wait before taking the screenshot in milliseconds (to make sure that the website is ready, optional, default: 250 milliseconds)

Example call: `java -jar website-screenshot-utility.jar "http://www.google.de/" 1920 1080 images/google.png`

## Known Bugs
This software is currently only used for personal projects, meaning that there is a lot of bug potential. Currently, the following bugs are known to me:

### Geckodriver and sometimes Firefox continue to run in the background
Workaround: Kill those processes. On linux, I do this using the following commands: `for process in $(pgrep firefox); do kill "${process}"; done` and `for process in $(pgrep geckodriver); do kill "${process}"; done`

## Authors

* **Christian Taeumel** - *Initial work*

See also the list of [contributors](https://github.com/CTaeumel/website-screenshot-utility/contributors) who participated in this project.

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE - see the [LICENSE](LICENSE) file for details

## Other Notes
* This software was developed for Java 8 in combination with Geckodriver 2.4 and Firefox 68
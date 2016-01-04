# onto-concept
extended version of ontology

## Usage

#### Download zip or Clone it using the following command

`https://github.com/project-spinoza/onto-concept.git`
#### How to build the application

Navigate to the onto-concept directory and run the following command

`mvn clean install`
#### Running the application

Run the following command from the root directory

`java -jar ontology/target/ontology-v1.0-jar-with-dependencies.jar tweets.txt ontologies.json` <br>
##### OR 
Run `run.sh` for linux and Mac<br>
Run `run.bat` for windows <br><br>

<b>Note:</b>
<li>You can replace `tweets.txt` with the file containing one tweet per line</li>

#### Result
The final result will be stored in two files. `ontology.txt` and `concept.txt` files, at root directory of project.

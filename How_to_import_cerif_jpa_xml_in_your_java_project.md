## Initialization ##

If you intend to use the package in more than one projects it is highly recommended to rename the _artifactId_ inside the project’s pom.xml file.

Because the _cerif-jpa-xml_ package depends on a _cerif-jpa-service_ package and because most likely you will use a specific services package in your project you will have to edit cerif-jpa-xml’s pom.xml file so as it contains a dependency for that specific services package. Note that you need to have the latest version of the services package and the latest version of the model package.

```
<dependency>
    <groupId>gr.ekt</groupId>
    <artifactId>cerif-jpa-services</artifactId>
    <version>0.1</version>
    <type>jar</type>
    <scope>compile</scope>
</dependency>
```

Edit the previous dependency for your own services package. If the _artifactId_ of the services package is _cerif-jpa-services_ you don’t have to change it.

The next step is to add a dependency in the project that will use the _cerif-jpa-xml_ package, e.g. the web app project. In the project’s pom.xml you will need to add the following dependency:

```
<dependency>
    <groupId>gr.ekt</groupId>
    <artifactId>cerif-jpa-xml</artifactId> //or whatever name you have chosen
    <version>0.1</version>
    <exclusions>
        <exclusion>
            <artifactId>cerif-jpa-services</artifactId>
            <groupId>gr.ekt</groupId>
        </exclusion>
    </exclusions>
</dependency> 
```

Note the exclusion. If the project already uses the services package then it needs to be excluded from the _cerif-jpa-xml_ dependency to avoid any discrepancy.

If you intend to use the _cerif-jpa-xml_ package in more than one projects then you must rename the _cerif2xml-context.xml_ file located in the _src/main/resources/META-INF_ folder.

Next you will need to add the following import entry in the context initialization xml file of the project that will use the _cerif-jpa-xml_ package.

```
<beans:import resource="classpath:/META-INF/cerif2xml-context.xml"/>
```

There are two parameters you can edit, the data source name which is a simple text that is displayed in the xml's header and the location of the Velocity (http://velocity.apache.org/) xml template in case you want to use a custom one. The parameters can be set in the pom.xml file of the _cerif-jpa-xml_ package inside a profile section.

```
<profiles>
    <profile>
        <id>profile1</id>
        <activation>
            <activeByDefault>false</activeByDefault>
        </activation>
        <properties>
           <xml.xmlSourceDatabase>cerif db</xml.xmlSourceDatabase>
	   <xml.template>/META-INF/cerif2xmlTemplate.xml</xml.template>
        </properties>
    </profile>   
</profiles>
```


You can edit the existing profile or add new ones to suit your needs.



## Using the package ##

To use the the _cerif-jpa-xml_ package you will have to inject in your class a _XmlCERIFService_ object as follows:

```
@Autowired
private XmlCERIFService xmlCERIFService;
```

This class offers a single function with a single parameter and returns a String containing the xml.

```
public String makeXML(LoadingSpecs spec)
```



### Retrieve a specific set of data using parameters ###

There are five different parameters:
  * _include_. You can use this parameter to retrieve specific entities. The list of entities that will be included in the file can be set by adding them as url parameters e.g. include=Project&include=Organisation&include=Person&include=Funding. All entities will be retrieved if _include_ is set to "all". The names that should be used are the ones dictated by CERIF (http://www.eurocris.org/Uploads/Web%20pages/CERIF-1.5/CERIF1.5_Semantics.xhtml). Available entities are the following, Project, Organisation, Person, Electronic Address, Postal Address, Funding, Classification, Classification Scheme, Publication, Facility, Product, Service, Equipment, Federated Identifiers and Measurement.
  * _exclude_. In similar fashion, you can use this parameter to exclude entities from the returned xml. If an entity is included in both parameters then the entity will be excluded.
  * _windowOffset_ and _windowSize_. The combination of these parameters can be used in order to retrieve a subset of data. _windowOffset_ sets the current page and _windowSize_ sets the size of the page. E.g. windowOffset=1&windowSize=100 will produce a file with 100 entities per type starting from 101th entity. If _windowSize_ is equal to zero or if it is not used then all data will be retrieved. Default value for both is 0 (produces full set).
  * _links_. Set this parameter to _false_ if you don't want to include link entities in your file. Default value is true.
  * _showFedIds_. Set this parameter to _true_ if you don't want to include federated identifiers in your file. Default value is false.


The following is a sample code of how the package can be used inside a Spring MVC controller function :

```
@RequestMapping(value = "/export", produces="application/xml")
public @ResponseBody String export(
      @RequestParam(required=false, value="include") List<String> include,
      @RequestParam(required=false, value="exclude") List<String> exclude,
      @RequestParam(required=false, value="windowOffset") Integer windowOffset,
      @RequestParam(required=false, value="windowSize") Integer windowSize,
      @RequestParam(required=false, value="links") String links,
      @RequestParam(required=false, value="showFedIds") String showFedIds,
      HttpServletRequest request) {
		
    	LoadingSpecs ls = new LoadingSpecs();
		
	if (include != null) {
		ls.setListOfEntitiesIncluded(include);
	} 
		
	if (exclude != null) {
		ls.setListOfEntitiesExcluded(exclude);
	} 
		
	if (windowOffset!=null) {
		ls.setWindowOffset(windowOffset);
	} 
		
	if (windowSize!=null) {
		ls.setWindowSize(windowSize);
	} 
		
	if (links!=null) {
		if (links.equalsIgnoreCase("true")) {
			ls.setLinks(true);
		} else {
			ls.setLinks(false);
		}
	} 
	
	if (showFedIds!=null) {
		if (showFedIds.equalsIgnoreCase("true")) {
			ls.setShowFedIds(true);
		} else {
			ls.setShowFedIds(false);
		}
	} 

	String resultText = cerifToXmlOutputGenerator.getResultText();*/
	
	String resultText = xmlCERIFService.makeXML(ls);
	
	return resultText;
		
}
```
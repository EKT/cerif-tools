There are two distinct Java projects, the _cerif-jpa-model_ which represents the CERIF data model and the _cerif-jpa-services_ which can be used to accommodate queries and custom functions and provides a mechanism for the programmer to access them. Each inner package in _cerif-jpa-services_ provides a transfer object class for multilingual fields.

The _cerif-jpa-model_ package doesn't need any editing or preparation. It can be used as is.

The package _cerif-jpa-services_ depends on the model package through a pom dependency entry (already present in its pom.xml).

The _app-context-cerif-jpa-services.xml_ file in _cerif-jpa-services_ is used to initialize the db connection either with a dataSource bean or with a jee:jndi-lookup bean.

Any client project, e.g. a UI app, must include as a pom dependency the _cerif-jpa-services_ package.

```
<dependency>
    <groupId>gr.ekt</groupId>
    <artifactId>cerif-jpa-services</artifactId>
    <version>0.1</version>
    <type>jar</type>
</dependency>
```

The needed queries and/or tranfer object classes can be added in the _cerif-jpa-services_ package.

The context xml of the client project must include an import command for the context xml file of the _cerif-jpa-services_ package e.g.

```
<beans:import resource="classpath:/META-INF/spring/app-context-cerif-jpa-services.xml"/>
```



## Example project ##

![http://cerif-jpa-persistence.googlecode.com/svn/trunk/CERIF%201.6/cerif.jpg](http://cerif-jpa-persistence.googlecode.com/svn/trunk/CERIF%201.6/cerif.jpg)

The project relationships between individual packages can be structured as in the image above. A parent project can contain the client project and the services package. The parameters for the application can be stored in the parent’s project pom.xml file inside pom profiles, e.g.

```
<profiles>
    <profile>
    <id>profile_id1</id>
        <activation>
            <activeByDefault>false</activeByDefault>
        </activation>
        <properties>
            <target.version>0.1</target.version>
            <db.driver>com.mysql.jdbc.Driver</db.driver>
            <db.url>jdbc:mysql://localhost:3306/db?characterEncoding=utf8</db.url>
            <db.username>root</db.username>
            <db.password>pass</db.password>
                       
            <!-- validate | update | create | create-drop -->
            <hibernate.hbm2ddl.auto>create</hibernate.hbm2ddl.auto>
            <hibernate.dialect>org.hibernate.dialect.MySQL5InnoDBDialect</hibernate.dialect>
            <hibernate.show_sql>true</hibernate.show_sql>
     	</properties>
     </profile>
<profiles>
```

The parameters can then be passed in the _app-context-cerif-jpa-services.xml_ as _${}_ beans parameters.

The pom.xml of the parent project must contain the following

```
<modules>
     <module>cerif-jpa-services</module>
     <module>client-project-artifactId</module>
</modules>
```

The services’ package pom.xml file and the client’s project pom.xml must include the following:

```
<parent>
    <groupId>parent’s version groupId</groupId>
    <artifactId>parent’s version artifactId</artifactId>
    <version>parent’s version</version>
</parent>
```

Depending on the type of database that is used, the appropriate JDBC driver should be included as a dependency in the The services’ package pom.xml. The file includes the following

```
<dependency>
    <groupId>postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>9.1-901-1.jdbc4</version>
    <type>jar</type>
    <scope>compile</scope>
</dependency>
		
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.18</version>
    <type>jar</type>
    <scope>compile</scope>
</dependency>
```
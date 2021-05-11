# Guide to compiling :rocket:

# 1. Install Java 16

[Java SE 16 Download page](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html)

After installing Java 16, set the JAVA_HOME environment variable to point to the JDKs folder, like so:

```bash
export JAVA_HOME=/usr/lib/jvm/jdk-16.0.1/
```

# 2. Install Maven 3.8.1

Since we are using Java 16, you need to be using Apache Maven 3.8.1  
[Download page](https://maven.apache.org/download.cgi)

- Extract the tar.gz archive somewhere safe :wink:

- edit your .bashrc file with  ```nano ~/.bashrc``` and add the following lines
  
  ```bash
  export M2_HOME=<path-to-extracted-maven-archive>
  export M2=$M2_HOME/bin
  export PATH=$M2:$PATH
  ```



You should be good to go now :smiling_imp:

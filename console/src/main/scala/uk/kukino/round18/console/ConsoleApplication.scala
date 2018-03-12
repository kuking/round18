package uk.kukino.round18.console

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

object ConsoleApplication {
    def main(args: Array[String]) : Unit = SpringApplication.run(classOf[ConsoleApplication], args :_ *)
}

@SpringBootApplication
class ConsoleApplication{}


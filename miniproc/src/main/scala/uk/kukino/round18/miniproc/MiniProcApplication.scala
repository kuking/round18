package uk.kukino.round18.miniproc

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching

object MiniProcApplication {
  def main(args: Array[String]): Unit = SpringApplication.run(classOf[MiniProcApplication], args: _ *)
}

@SpringBootApplication
@EnableCaching
class MiniProcApplication {}

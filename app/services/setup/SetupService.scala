package services.setup

import conf.security.{AuthUtil, RolesID}
import domain.sites.Site
import domain.users.{User, UserRole}
import org.joda.time.DateTime
import repositories.sites.{AdministratorsRepository, SiteRepository}
import repositories.util.{KeysRepository, MailRepository, RoleRepository, TokenRepository}
import services.Service

import scala.concurrent.ExecutionContext.Implicits.global


/**
  * Created by kuminga on 2016/08/18.
  */
object SetupService extends Service {

  val organisation = Site("HASHCODE", "Hashcode", "hash-code.com")


  val testUser = User(
    "HASHCODE",
    "test@admin.com",
    "ADMINSTRATOR",
    None,
    None,
    AuthUtil.encode("admin"))


  val userRole = UserRole("admin@test.com", RolesID.ADMIN, new DateTime())

  def runSetUp = for {
  // Add Your respositories to create Tables Here
    createTable <- KeysRepository.create.ifNotExists().future()
    createTable <- MailRepository.create.ifNotExists().future()
    createTable <- RoleRepository.create.ifNotExists().future()
    createTable <- TokenRepository.create.ifNotExists().future()

    createTable <- SiteRepository.create.ifNotExists().future()
    createTable <- AdministratorsRepository.create.ifNotExists()

  } yield createTable

  runSetUp
}

package services.setup

import conf.security.{AuthUtil, RolesID}
import domain.sites.Site
import domain.users.{User, UserRole}
import org.joda.time.DateTime
import repositories.comments._
import repositories.sites.{AdministratorsRepository, SiteRepository}
import repositories.syslog.SystemLogEventsRepository
import repositories.users.{ReputationRepository, UserRepository, UserRoleRepository, UserStatusRepository}
import repositories.util._
import repositories.votes.{UserDownVotesRepository, UserUpVotesRepository, VoteDownRepository, VoteUpRepository}
import services.Service

import scala.concurrent.ExecutionContext.Implicits.global


/**
  * Created by kuminga on 2016/08/18.
  */
object SetupService extends Service {


  val site = Site("HASHCODE", "Hashcode", "hash-code.com")


  val testAdmin = User(
    "HASHCODE",
    "test@admin.com",
    "ADMINSTRATOR",
    Some("System"),
    Some("System"),
    AuthUtil.encode("admin"),
    new DateTime)

  val testUser = User(
    "HASHCODE",
    "test@test.com",
    "ANONYMOUS",
    None,
    None,
    AuthUtil.encode("admin"),
    new DateTime)


  val adminRole = UserRole("hashcode.zm", "admin@test.com", RolesID.ADMIN, new DateTime)

  val userRole = UserRole("hashcode.zm", "test@test.com", RolesID.ANONYMOUS_USER, new DateTime)

  def runSetUp = for {
  //Comments
    table <- AbuseByUserRepository.create.ifNotExists().future()
    table <- AbuseRepository.create.ifNotExists().future()
    table <- CommentRepository.create.ifNotExists().future()
    table <- CommentsByUserRepository.create.ifNotExists().future()
    table <- CommentStatusRepository.create.ifNotExists().future()
    table <- ResponseRepository.create.ifNotExists().future()
    table <- ResponseStatusRepository.create.ifNotExists().future()
    table <- ResponseByUserRepository.create.ifNotExists().future()
    table <- SingleCommentRepository.create.ifNotExists().future()
    table <- SingleResponseRepository.create.ifNotExists().future()
    table <- SubjectRepository.create.ifNotExists().future()



    //SysLog
    table <- SystemLogEventsRepository.create.ifNotExists().future()

    //users
    table <- ReputationRepository.create.ifNotExists().future()
    table <- UserRepository.create.ifNotExists().future()
    table <- UserRoleRepository.create.ifNotExists().future()
    table <- UserStatusRepository.create.ifNotExists().future()

    //Add Your respositories to create Tables Here

    createTable <- KeysRepository.create.ifNotExists().future()
    createTable <- MailRepository.create.ifNotExists().future()
    createTable <- RoleRepository.create.ifNotExists().future()
    createTable <- TokenRepository.create.ifNotExists().future()
    createtable <- ItemStatusRepository.create.ifNotExists().future()

    //Admin

    createTable <- SiteRepository.create.ifNotExists().future()
    createTable <- AdministratorsRepository.create.ifNotExists().future()

    //Votes
    table <- UserDownVotesRepository.create.ifNotExists().future()
    table <- UserUpVotesRepository.create.ifNotExists().future()
    table <- VoteDownRepository.create.ifNotExists().future()
    table <- VoteUpRepository.create.ifNotExists().future()

    saveTable <- UserRepository.save(testAdmin)
    saveRole <- UserRoleRepository.save(userRole)
    saveTable <- UserRepository.save(testUser)
    saveTable <- UserRoleRepository.save(adminRole)

  } yield table

}

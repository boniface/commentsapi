package services.setup

import conf.security.AuthUtil
import domain.organisation.Organisation
import domain.people.{User, UserRole}
import domain.users.User
import repositories.address.{AddressTypeRepository, GlobalLocationRepository, LocationTypeRepository}
import repositories.contacts._
import repositories.financials._
import repositories.messages.MessagesRepository
import repositories.organisation._
import repositories.people._
import repositories.portfolio.{AdvisorPortfolioRepository, AdvisorPortfolioStatusRepository, FundManagerRepository, FundPortfolioStatusRepository}
import repositories.subscriptions.SubscriptionsRepository
import repositories.syslogs.SystemLogEventsRepository
import repositories.util._
import services.Service

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by kuminga on 2016/08/18.
  */
object SetupService extends Service {
  val details = Map("contactEmail" -> "admin@test.com")
  val organisation = Organisation("MARGINM", "Margin Mentor", "COMMERCIAL", "ACTIVE", "admin@test.com", details)

  val testUser = User("admin@test.com",
    "System",
    "Admininstrator",
    "Admin",
    AuthUtil.encode("admin"),
    "ACTIVE",
    "MARGINM")


  def UserRole(value: Nothing, value1: Nothing, value2: Nothing) = ???

  val userRole = UserRole("MARGINM", "admin@test.com", "ADMIN")

  def runSetUp = for {

  // Add Your respositories to create Tables Here


    createTable <- KeysRepository.create.ifNotExists().future()
    createTable <- MailRepository.create.ifNotExists().future()
    createTable <- TokenRepository.create.ifNotExists().future()



    createTable <- KeysRepository.create.ifNotExists().future()
    createTable <- MailRepository.create.ifNotExists().future()
    createTable <- RoleRepository.create.ifNotExists().future()
    createTable <- TokenRepository.create.ifNotExists().future()


  } yield createTable
}

package services.util

import com.websudos.phantom.dsl.ResultSet
import domain.util.Roles
import services.util.Impl.RolesServiceImpl

import scala.concurrent.Future


trait RoleService {
  def create(entity: Roles): Future[ResultSet]

  def getRoleById(id: String): Future[Option[Roles]]

  def getAll: Future[Seq[Roles]]
}

object RoleService{
  def apply: RoleService = new RolesServiceImpl()
}

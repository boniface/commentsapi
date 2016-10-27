package services.util.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.util.Roles
import repositories.Util.RolesRepository
import services.Service
import services.util.RoleService

import scala.concurrent.Future


class RolesServiceImpl extends RoleService with Service{
  override def create(entity: Roles): Future[ResultSet] = {
    RolesRepository.save(entity)
  }

  override def getRoleById(id: String): Future[Option[Roles]] = {
    RolesRepository.getRoleById(id)
  }

  override def getAll(): Future[Seq[Roles]] = {
    RolesRepository.getAllRoles
  }
}

package lectures.part2oop

import lectures.part2oop.Enums.Permissions.READ

object Enums extends App{

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // add fields and methods
    def openDocument(): Unit =
      if (this == READ) println("opening document...")
      else println("reading not allowed.")
  }

  val somePermissions: Permissions = READ

  somePermissions.openDocument()

  // enums can have constructor args
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  //Enums can have companion object
  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = //whatever
      NONE
  }

  // standard API
  val somePermissionsOrdinal = somePermissions.ordinal
  println(somePermissionsOrdinal)
  val allPermissions = PermissionsWithBits.values
  val redPermission: Permissions = Permissions.valueOf("READ")
}

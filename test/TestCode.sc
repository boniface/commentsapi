
import shapeless.{HList, ::, HNil}

sealed trait Shape

final case class Rectangle(width:Double, height:Double) extends Shape
final case class Circle(radius:Double) extends Shape

val rect:Shape = Rectangle(3.0,4.0)
val circ:Shape = Circle(1.0)

def area(shape:Shape): Double ={
  shape match {
    case Rectangle(w, h) => w * h
    case Circle(r) => math.Pi*r*r
  }
}

area(rect)
area(circ)

type Rectangle2 = (Double,Double)

type Circle2 = Double

type Shape2 = Either[Rectangle2,Circle2]

val rect2: Shape2 = Left(3.0,4.0)
val circ2:Shape2 = Right(1.0)


def area2(shape:Shape2):Double ={
  shape match {
    case Left((w,h))=> w*h
    case Right(r) => math.Pi*r*r
  }
}


area2(rect2)
area2(circ2)

val product:String :: Int :: Boolean :: HNil = "Sunday" :: 1 :: false :: HNil
val first = product.head
val second = product.tail.head
val rest = product.tail.tail



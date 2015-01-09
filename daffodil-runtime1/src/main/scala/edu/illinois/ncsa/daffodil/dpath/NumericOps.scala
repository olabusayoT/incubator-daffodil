package edu.illinois.ncsa.daffodil.dpath

import edu.illinois.ncsa.daffodil.processors._
import scala.collection.mutable.Stack
import scala.collection.mutable.ListBuffer
import edu.illinois.ncsa.daffodil.exceptions._
import edu.illinois.ncsa.daffodil.util.Maybe
import edu.illinois.ncsa.daffodil.util.Maybe._
import edu.illinois.ncsa.daffodil.xml.RefQName
import edu.illinois.ncsa.daffodil.util.Misc
import edu.illinois.ncsa.daffodil.dsom._
import edu.illinois.ncsa.daffodil.xml.XMLUtils
import edu.illinois.ncsa.daffodil.util.OnStack
import edu.illinois.ncsa.daffodil.util.PreSerialization
import com.ibm.icu.text.SimpleDateFormat
import com.ibm.icu.util.Calendar
import scala.math.BigDecimal.RoundingMode
import edu.illinois.ncsa.daffodil.util.Bits
import edu.illinois.ncsa.daffodil.compiler.DaffodilTunableParameters
import java.text.ParsePosition
import com.ibm.icu.util.SimpleTimeZone
import com.ibm.icu.util.TimeZone
import java.nio.ByteBuffer
import AsIntConverters._

case object PlusDecimal extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigDecimal(v1) + asBigDecimal(v2) }
}
case object MinusDecimal extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigDecimal(v1) - asBigDecimal(v2) }
}
case object TimesDecimal extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigDecimal(v1) * asBigDecimal(v2) }
}
case object DivDecimal extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigDecimal(v1) / asBigDecimal(v2) }
}
case object IDivDecimal extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigDecimal(v1) / asBigDecimal(v2) }
}
case object ModDecimal extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigDecimal(v1) % asBigDecimal(v2) }
}

case object PlusInteger extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) + asBigInt(v2) }
}
case object MinusInteger extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) - asBigInt(v2) }
}
case object TimesInteger extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) * asBigInt(v2) }
}
case object DivInteger extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) / asBigInt(v2) }
}
case object IDivInteger extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) / asBigInt(v2) }
}
case object ModInteger extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) % asBigInt(v2) }
}

case object PlusNonNegativeInteger extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) + asBigInt(v2) }
}
case object MinusNonNegativeInteger extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) - asBigInt(v2) }
}
case object TimesNonNegativeInteger extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) * asBigInt(v2) }
}
case object DivNonNegativeInteger extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) / asBigInt(v2) }
}
case object IDivNonNegativeInteger extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) / asBigInt(v2) }
}
case object ModNonNegativeInteger extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) % asBigInt(v2) }
}

case object PlusUnsignedLong extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) + asBigInt(v2) }
}
case object MinusUnsignedLong extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) - asBigInt(v2) }
}
case object TimesUnsignedLong extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) * asBigInt(v2) }
}
case object DivUnsignedLong extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) / asBigInt(v2) }
}
case object IDivUnsignedLong extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) / asBigInt(v2) }
}
case object ModUnsignedLong extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asBigInt(v1) % asBigInt(v2) }
}

case object PlusLong extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asLong(v1) + asLong(v2) }
}
case object MinusLong extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asLong(v1) - asLong(v2) }
}
case object TimesLong extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asLong(v1) * asLong(v2) }
}
case object DivLong extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asLong(v1) / asLong(v2) }
}
case object IDivLong extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asLong(v1) / asLong(v2) }
}
case object ModLong extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asLong(v1) % asLong(v2) }
}

case object PlusUnsignedInt extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asLong(v1) + asLong(v2) }
}
case object MinusUnsignedInt extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asLong(v1) - asLong(v2) }
}
case object TimesUnsignedInt extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asLong(v1) * asLong(v2) }
}
case object DivUnsignedInt extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asLong(v1) / asLong(v2) }
}
case object IDivUnsignedInt extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asLong(v1) / asLong(v2) }
}
case object ModUnsignedInt extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asLong(v1) % asLong(v2) }
}

case object PlusInt extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asInt(v1) + asInt(v2) }
}
case object MinusInt extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asInt(v1) - asInt(v2) }
}
case object TimesInt extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asInt(v1) * asInt(v2) }
}
case object DivInt extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asInt(v1) / asInt(v2) }
}
case object IDivInt extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asInt(v1) / asInt(v2) }
}
case object ModInt extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asInt(v1) % asInt(v2) }
}

case object PlusUnsignedShort extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asInt(v1) + asInt(v2) }
}
case object MinusUnsignedShort extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asInt(v1) - asInt(v2) }
}
case object TimesUnsignedShort extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asInt(v1) * asInt(v2) }
}
case object DivUnsignedShort extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asInt(v1) / asInt(v2) }
}
case object IDivUnsignedShort extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asInt(v1) / asInt(v2) }
}
case object ModUnsignedShort extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asInt(v1) % asInt(v2) }
}

case object PlusShort extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asShort(v1) + asShort(v2) }
}
case object MinusShort extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asShort(v1) - asShort(v2) }
}
case object TimesShort extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asShort(v1) * asShort(v2) }
}
case object DivShort extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asShort(v1) / asShort(v2) }
}
case object IDivShort extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asShort(v1) / asShort(v2) }
}
case object ModShort extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asShort(v1) % asShort(v2) }
}

case object PlusUnsignedByte extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asShort(v1) + asShort(v2) }
}
case object MinusUnsignedByte extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asShort(v1) - asShort(v2) }
}
case object TimesUnsignedByte extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asShort(v1) * asShort(v2) }
}
case object DivUnsignedByte extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asShort(v1) / asShort(v2) }
}
case object IDivUnsignedByte extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asShort(v1) / asShort(v2) }
}
case object ModUnsignedByte extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asShort(v1) % asShort(v2) }
}

case object PlusByte extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asByte(v1) + asByte(v2) }
}
case object MinusByte extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asByte(v1) - asByte(v2) }
}
case object TimesByte extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asByte(v1) * asByte(v2) }
}
case object DivByte extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asByte(v1) / asByte(v2) }
}
case object IDivByte extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asByte(v1) / asByte(v2) }
}
case object ModByte extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asByte(v1) % asByte(v2) }
}

case object PlusFloat extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asFloat(v1) + asFloat(v2) }
}
case object MinusFloat extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asFloat(v1) - asFloat(v2) }
}
case object TimesFloat extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asFloat(v1) * asFloat(v2) }
}
case object DivFloat extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asFloat(v1) / asFloat(v2) }
}
case object IDivFloat extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asFloat(v1) / asFloat(v2) }
}
case object ModFloat extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asFloat(v1) % asFloat(v2) }
}

case object PlusDouble extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asDouble(v1) + asDouble(v2) }
}
case object MinusDouble extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asDouble(v1) - asDouble(v2) }
}
case object TimesDouble extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asDouble(v1) * asDouble(v2) }
}
case object DivDouble extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asDouble(v1) / asDouble(v2) }
}
case object IDivDouble extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asDouble(v1) / asDouble(v2) }
}
case object ModDouble extends NumericOp {
  def operate(v1: Any, v2: Any): Any = { asDouble(v1) % asDouble(v2) }
}
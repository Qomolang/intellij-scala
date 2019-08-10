package org.jetbrains.plugins.scala.lang.typeConformance
package generated

class TypeConformanceCompoundTest extends TypeConformanceTestBase {
  //This class was generated by build script, please don't change this
  override def folderPath: String = super.folderPath + "compound/"

  def testAWithB() {doTest()}

  def testAWithBWithC() {doTest()}

  def testAWithBWithMissingB() {doTest()}

  def testAWithBWithMissingDef() {doTest()}

  def testAWithBWithMissingType() {doTest()}

  def testAWithBWithTemplateDef() {doTest()}

  def testAWithBWithTemplateDef1() {doTest()}

  def testAWithBWithType() {doTest()}

  def testAWithBWithTypeInC() {doTest()}

  def testAdditionalTemplateBody() {doTest()}

  def testBugScl1996() {doTest()}

  def testWrongName() {doTest()}

  def testnoAdditionalTemplateBody() {doTest()}

  def testAnonImplementation(): Unit = doTest(
    """
      |trait Foo {
      |  def foo: Unit
      |}
      |
      |val newFoo: Foo = new Foo {
      |  override def foo: Unit = ()
      |}
      |//True
    """.stripMargin, checkEquivalence = true)

  def testVarRefinement(): Unit = doTest(
    """
      |val v: { var zzz: Int } = new {
      |  def zzz: Int = 0
      |  def zzz_=(i: Int): Unit = ()
      |}
      |//True
      |""".stripMargin
  )

  def testVarRefinement2(): Unit = doTest(
    """
      |val v: {def zzz: Int; def zzz_=(i: Int): Unit} = new {var zzz: Int = 0}
      |//True
      |""".stripMargin
  )
}
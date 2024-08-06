class MySuite extends munit.FunSuite {
  test("example test that succeeds") {
    val obtained = 42
    val expected = 42
    assertEquals(obtained, expected)
  }

  //**TODO: Your tests here
  // test("testing many val error") {
  //   val manyExprs = ManyExprs(List(Happy, a))


  //   val exception = intercept[RuntimeException] {
  //     manyExprs.eval
  //   }
  //   assert(exception.getMessage === "Error Evaluating")
  // }
}

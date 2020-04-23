import kotlin.test.Test
import kotlin.test.assertEquals

class SampleTest {

    @Test
    fun testReturnValue() {
        assertEquals("Hello from Kotlin, John!", KotlinNativeFramework().helloFromKotlin("John"))
    }

}
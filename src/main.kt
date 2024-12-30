import korlibs.event.*
import korlibs.time.*
import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.korge.view.*
import korlibs.image.color.*
import korlibs.image.format.*
import korlibs.io.file.std.*
import korlibs.korge.view.align.*
import korlibs.math.geom.*

suspend fun main() = Korge(windowSize = Size(512, 512), backgroundColor = Colors["#2b2b2b"]) {
	val sceneContainer = sceneContainer()

	sceneContainer.changeTo { MyScene() }
}

class MyScene : Scene() {
	override suspend fun SContainer.sceneMain() {
		val spriteMap = resourcesVfs["Pet Cats Pack/Cat-1/Cat-1-Laying.png"].readBitmap()
        val layingAnimation = SpriteAnimation(
            spriteMap = spriteMap,
            spriteWidth = 50,
            spriteHeight = 50,
            columns = 8,
        )

        var x = 56
        for (frame in layingAnimation.sprites) {
            image(frame) {
                smoothing = false
                xy(x, 50)
            }

            x += 50
        }

        val sprite = sprite(layingAnimation) {
            smoothing = false
            scale = 3.0
            centerOnStage()

            addUpdater {
                if (input.keys[Key.SPACE]) {
                    playAnimation(spriteDisplayTime = 200.fastMilliseconds)
                }
            }
        }

        text("${sprite.totalFramesPlayed}", textSize = 24) {
            centerXOnStage()
            y = 400.0

            addUpdater { text = "${sprite.totalFramesPlayed}" }
        }
	}
}

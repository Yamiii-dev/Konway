import org.openrndr.KEY_BACKSPACE
import org.openrndr.KEY_ENTER
import org.openrndr.KEY_SPACEBAR
import org.openrndr.MouseButton
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.loadFont
import org.openrndr.draw.loadImage
import org.openrndr.extra.color.colormatrix.tint
import org.openrndr.extra.color.presets.DARK_GRAY
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

const val GRID_WIDTH = 50
const val GRID_HEIGHT = 30
const val GRID_SIZE = 20

fun main() = application {
    configure {
        width = GRID_WIDTH * GRID_SIZE
        height = GRID_HEIGHT * GRID_SIZE
        title = "KONWAY"
    }

    program {
        var grid: Array<BooleanArray> = Array<BooleanArray>(GRID_WIDTH) { BooleanArray(GRID_HEIGHT) }

        mouse.buttonDown.listen {
            if(it.button == MouseButton.LEFT){
                val x: Int = (it.position.x / GRID_SIZE).toInt()
                val y: Int = (it.position.y / GRID_SIZE).toInt()
                grid[x][y] = !grid[x][y]
            }
        }
        var running = false

        keyboard.keyDown.listen {
            if(it.key == KEY_SPACEBAR) running = !running
            else if(it.key == KEY_BACKSPACE){
                grid = Array<BooleanArray>(GRID_WIDTH) { BooleanArray(GRID_HEIGHT) }
            }
            else if(it.key == KEY_ENTER){
                for(y in 0..<GRID_HEIGHT){
                    for(x in 0..<GRID_WIDTH){
                        grid[x][y] = Random.nextBoolean()
                    }
                }
            }
        }

        extend {
            drawer.stroke = null
            if(!running)
                drawer.fill = ColorRGBa.GRAY
            else
                drawer.fill = ColorRGBa.WHITE
            drawer.rectangle(0.0, 0.0, width.toDouble(), height.toDouble())
            drawer.stroke = ColorRGBa.DARK_GRAY
            if(running)
                drawer.stroke = ColorRGBa.WHITE
            drawer.strokeWeight = 0.25

            if(frameCount%10==0 && running) {
                val newGrid: Array<BooleanArray> = Array<BooleanArray>(GRID_WIDTH) { BooleanArray(GRID_HEIGHT) }
                for (y in 0..<GRID_HEIGHT) {
                    for (x in 0..<GRID_WIDTH) {
                        var aliveNeighbors = 0
                        for (vy in -1..1) {
                            for (vx in -1..1) {
                                if (vy == 0 && vx == 0) continue
                                if (x + vx !in 0..<GRID_WIDTH || y + vy !in 0..<GRID_HEIGHT) continue
                                if (grid[x + vx][y + vy]) aliveNeighbors++
                            }
                        }


                        newGrid[x][y] =
                            if (grid[x][y])
                                aliveNeighbors in 2..3
                            else
                                aliveNeighbors == 3
                    }
                }
                grid = newGrid
            }
            for(y in 0..<GRID_HEIGHT) {
                for (x in 0..<GRID_WIDTH) {
                    if (grid[x][y])
                        drawer.fill = ColorRGBa.BLACK
                    else
                        drawer.fill = null
                    drawer.rectangle(
                        (x * GRID_SIZE).toDouble(),
                        (y * GRID_SIZE).toDouble(),
                        GRID_SIZE.toDouble(),
                        GRID_SIZE.toDouble()
                    )
                }
            }
        }
    }
}
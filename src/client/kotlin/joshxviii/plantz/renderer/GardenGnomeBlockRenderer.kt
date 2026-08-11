package joshxviii.plantz.renderer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import joshxviii.plantz.block.GardenGnomeBlock
import joshxviii.plantz.block.GardenGnomeColor
import joshxviii.plantz.block.TimeMachineBlock
import joshxviii.plantz.block.TimeMachineState
import joshxviii.plantz.block.entity.GardenGnomeBlockEntity
import joshxviii.plantz.block.entity.TimeMachineBlockEntity
import joshxviii.plantz.model.GnomeModel
import joshxviii.plantz.renderer.entity.GnomeRenderState
import joshxviii.plantz.renderer.entity.PazZombieRenderState
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState
import net.minecraft.client.renderer.feature.ModelFeatureRenderer
import net.minecraft.client.renderer.state.level.CameraRenderState
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.core.Direction
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth
import net.minecraft.world.level.block.state.properties.RotationSegment
import net.minecraft.world.phys.Vec3
import kotlin.math.pow

class GardenGnomeBlockRenderer(
    val gnomeModel: GnomeModel<GnomeRenderState>,
) : BlockEntityRenderer<GardenGnomeBlockEntity, GardenGnomeBlockRenderState> {
    val gnomeState: GnomeRenderState = GnomeRenderState()
    override fun createRenderState(): GardenGnomeBlockRenderState {
        return GardenGnomeBlockRenderState()
    }

    fun getTextureLocation(state: GardenGnomeBlockRenderState): Identifier {
        val texture = state.getTextureLocation(GardenGnomeBlockRenderState.TEXTURE_PATH + "/" + state.color.name)
        return texture
    }

    override fun extractRenderState(
        blockEntity: GardenGnomeBlockEntity,
        state: GardenGnomeBlockRenderState,
        partialTicks: Float,
        cameraPosition: Vec3,
        breakProgress: ModelFeatureRenderer.CrumblingOverlay?
    ) {
        val blockState = blockEntity.blockState
        val dir = blockState.getValue(GardenGnomeBlock.FACING)
        state.pose
        state.color = blockState.getValue(GardenGnomeBlock.COLOR)
        state.rotation = Direction.getYRot(dir)
        super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress)
    }

    override fun submit(
        state: GardenGnomeBlockRenderState,
        poseStack: PoseStack,
        collector: SubmitNodeCollector,
        camera: CameraRenderState
    ) {
        poseStack.pushPose()
        poseStack.translate(0.5, 1.5, 0.5)
        poseStack.mulPose(Axis.XP.rotationDegrees(180.0f))
        poseStack.mulPose(Axis.YP.rotationDegrees(state.rotation))
        collector.submitModel(
            gnomeModel,
            gnomeState,
            poseStack,
            getTextureLocation(state),
            state.lightCoords,
            OverlayTexture.NO_OVERLAY,
            gnomeState.outlineColor,
            null
        )
        poseStack.popPose()
    }
}

class GardenGnomeBlockRenderState : BlockEntityRenderState() {
    companion object {
        const val TEXTURE_PATH = "textures/block/garden_gnome"
    }
    var rotation: Float = 0f
    var color: GardenGnomeColor = GardenGnomeColor.BLUE
    var pose: GardenGnomePose = GardenGnomePose.NONE
}

enum class GardenGnomePose {
    NONE,
    WAVE,
    RELAX,
    SIT,
    THINK
}
package joshxviii.plantz

import com.mojang.blaze3d.PrimitiveTopology
import com.mojang.blaze3d.pipeline.BlendFunction
import com.mojang.blaze3d.pipeline.BindGroupLayout
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.DepthStencilState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.shaders.UniformType
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import net.minecraft.client.renderer.BindGroupLayouts
import net.fabricmc.loader.api.FabricLoader
import net.irisshaders.iris.api.v0.IrisApi
import net.irisshaders.iris.api.v0.IrisProgram
import net.minecraft.client.renderer.RenderPipelines

object PazRenderPipelines {

    private val PAINT_OVERLAY_BIND_GROUP = BindGroupLayout.builder()
        .withUniform("PaintInfo", UniformType.UNIFORM_BUFFER)
        .build()

    private val MATRICES_FOG_SNIPPET = RenderPipeline.builder()
        .withBindGroupLayout(BindGroupLayouts.MATRICES_PROJECTION)
        .withBindGroupLayout(BindGroupLayouts.FOG)
        .buildSnippet()

    @JvmField
    val ELECTRIC_ARC = RenderPipelines.register(
        RenderPipeline.builder(MATRICES_FOG_SNIPPET)
            .withLocation("pipeline/energy_swirl")
            .withVertexShader("core/entity")
            .withFragmentShader("core/entity")
            .withShaderDefine("ALPHA_CUTOUT", 0.1f)
            .withShaderDefine("EMISSIVE")
            .withShaderDefine("NO_OVERLAY")
            .withShaderDefine("NO_CARDINAL_LIGHTING")
            .withShaderDefine("APPLY_TEXTURE_MATRIX")
            .withBindGroupLayout(BindGroupLayouts.SAMPLER0)
            .withColorTargetState(ColorTargetState(BlendFunction.ADDITIVE))
            .withCull(false)
            .withVertexBinding(0, DefaultVertexFormat.ENTITY)
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .build())

    @JvmField
    val PAINT_OVERLAY: RenderPipeline = RenderPipelines.register(
        RenderPipeline.builder(MATRICES_FOG_SNIPPET)
            .withBindGroupLayout(BindGroupLayouts.SAMPLER0)
            .withBindGroupLayout(PAINT_OVERLAY_BIND_GROUP)
            .withLocation(pazResource("pipeline/paint_overlay"))
            .withVertexShader("core/entity")
            .withFragmentShader(pazResource("core/paint_overlay"))
            .withShaderDefine("ALPHA_CUTOUT", 0.1f)
            .withColorTargetState(ColorTargetState(BlendFunction.TRANSLUCENT))
            .withCull(false)
            .withVertexBinding(0, DefaultVertexFormat.ENTITY)
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .build()
    )

    @JvmField
    val HEAD_MARKER: RenderPipeline = RenderPipelines.register(
        RenderPipeline.builder(RenderPipelines.GUI_TEXTURED_SNIPPET)
            .withLocation(pazResource("pipeline/head_marker"))
            .build()
    )
    @JvmField
    val HEAD_MARKER_OUTLINE: RenderPipeline = RenderPipelines.register(
        RenderPipeline.builder(RenderPipelines.GUI_TEXTURED_SNIPPET)
            .withLocation(pazResource("pipeline/head_marker_outline"))
            .withFragmentShader(pazResource("core/head_marker_outline"))
            .build()
    )

    fun initialize() {
        if (FabricLoader.getInstance().isModLoaded("iris")) {
            registerIrisCompat()
        }
    }

    private fun registerIrisCompat() {
        try {
            IrisApi.getInstance().let{
                it.assignPipeline(
                    PAINT_OVERLAY,
                    IrisProgram.ENTITIES
                )
            }

        } catch (t: Throwable) {
            PazMain.LOGGER.warn("Iris present but assignPipeline failed", t)
        }
    }
}

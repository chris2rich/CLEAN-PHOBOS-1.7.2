package me.earth.phobos.features.modules.movement;

import me.earth.phobos.event.events.MoveEvent;
import me.earth.phobos.features.modules.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SafeWalk extends Module {
   public SafeWalk() {
      super("SafeWalk", "Walks safe", Module.Category.MOVEMENT, true, false, false);
   }

   @SubscribeEvent
   public void onMove(MoveEvent event) {
      if (event.getStage() == 0) {
         double x = event.getX();
         double y = event.getY();
         double z = event.getZ();
         if (mc.field_71439_g.field_70122_E) {
            double increment = 0.05D;

            while(x != 0.0D && this.isOffsetBBEmpty(x, -1.0D, 0.0D)) {
               if (x < increment && x >= -increment) {
                  x = 0.0D;
               } else if (x > 0.0D) {
                  x -= increment;
               } else {
                  x += increment;
               }
            }

            while(z != 0.0D && this.isOffsetBBEmpty(0.0D, -1.0D, z)) {
               if (z < increment && z >= -increment) {
                  z = 0.0D;
               } else if (z > 0.0D) {
                  z -= increment;
               } else {
                  z += increment;
               }
            }

            label64:
            while(true) {
               while(true) {
                  if (x == 0.0D || z == 0.0D || !this.isOffsetBBEmpty(x, -1.0D, z)) {
                     break label64;
                  }

                  if (x < increment && x >= -increment) {
                     x = 0.0D;
                  } else if (x > 0.0D) {
                     x -= increment;
                  } else {
                     x += increment;
                  }

                  if (z < increment && z >= -increment) {
                     z = 0.0D;
                  } else if (z > 0.0D) {
                     z -= increment;
                  } else {
                     z += increment;
                  }
               }
            }
         }

         event.setX(x);
         event.setY(y);
         event.setZ(z);
      }

   }

   public boolean isOffsetBBEmpty(double offsetX, double offsetY, double offsetZ) {
      EntityPlayerSP playerSP = mc.field_71439_g;
      return mc.field_71441_e.func_184144_a(playerSP, playerSP.func_174813_aQ().func_72317_d(offsetX, offsetY, offsetZ)).isEmpty();
   }
}

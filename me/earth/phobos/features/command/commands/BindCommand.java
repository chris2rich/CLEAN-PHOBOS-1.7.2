package me.earth.phobos.features.command.commands;

import me.earth.phobos.Phobos;
import me.earth.phobos.features.command.Command;
import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.setting.Bind;
import org.lwjgl.input.Keyboard;

public class BindCommand extends Command {
   public BindCommand() {
      super("bind", new String[]{"<module>", "<bind>"});
   }

   public void execute(String[] commands) {
      if (commands.length == 1) {
         sendMessage("Please specify a module.");
      } else {
         String rkey = commands[1];
         String moduleName = commands[0];
         Module module = Phobos.moduleManager.getModuleByName(moduleName);
         if (module == null) {
            sendMessage("Unknown module '" + module + "'!");
         } else if (rkey == null) {
            sendMessage(module.getName() + " is bound to &b" + module.getBind().toString());
         } else {
            int key = Keyboard.getKeyIndex(rkey.toUpperCase());
            if (rkey.equalsIgnoreCase("none")) {
               key = -1;
            }

            if (key == 0) {
               sendMessage("Unknown key '" + rkey + "'!");
            } else {
               module.bind.setValue(new Bind(key));
               sendMessage("Bind for &b" + module.getName() + "&r set to &b" + rkey.toUpperCase());
            }
         }
      }
   }
}

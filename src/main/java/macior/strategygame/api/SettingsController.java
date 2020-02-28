package macior.strategygame.api;

import macior.strategygame.models.settings_management.GetSettingsResponse;
import macior.strategygame.models.settings_management.Settings;
import macior.strategygame.models.settings_management.SettingsResponse;
import macior.strategygame.service.menu.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/settings")
@RestController
public class SettingsController {

    @Autowired
    private SettingsService settingsService;


    @PutMapping(path = "{code}")
    @CrossOrigin
    public SettingsResponse updateSettings(@RequestBody Settings settings, @PathVariable("code") String code){
        return settingsService.updateSettings(settings, code);
    }

    @GetMapping(path = "{code}")
    @CrossOrigin
    public GetSettingsResponse getSettings(@PathVariable("code") String code){
        return settingsService.getSettings(code);
    }
}

package macior.strategygame.service.menu;

import macior.strategygame.dao.SettingsDAO;
import macior.strategygame.models.User;
import macior.strategygame.models.settings_management.GetSettingsResponse;
import macior.strategygame.models.settings_management.Settings;
import macior.strategygame.models.settings_management.SettingsResponse;
import macior.strategygame.service.utilities.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    @Autowired
    @Qualifier("settingsDAO")
    private SettingsDAO settingsDAO;

    @Autowired
    private PlayerGameMapperService mapper;

    public SettingsResponse updateSettings(Settings settings, String code){
        int usersId = mapper.getId(code);
        SettingsResponse response = new SettingsResponse();
        int result = -1;
        if (usersId != -1){
            User user = new User();
            user.setId(usersId);
            result = settingsDAO.updateSettings(settings, user);
        }
        String newCode = mapper.updateCode(code);
        response.setCode(newCode);
        response.setStatus(result);
        return response;
    }

    public GetSettingsResponse getSettings(String code){
        int usersId = mapper.getId(code);
        Settings settings = null;
        GetSettingsResponse response = new GetSettingsResponse();
        if (usersId != -1){
            settings = settingsDAO.getSettings(usersId);
            if (settings != null){
                settings.setUser(null); //hiding info about the user
            }
        }
        String newCode = mapper.updateCode(code);
        response.setCode(newCode);
        response.setSettings(settings);
        return response;
    }
}

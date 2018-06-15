package com.barysevich.project.core.template;


import com.barysevich.project.api.TemplateService;
import com.barysevich.project.api.common.GetTemplateError;
import com.barysevich.project.api.request.GetTemplateRequest;
import com.barysevich.project.localization.Locale;
import com.barysevich.project.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class TemplateServiceImpl implements TemplateService
{
    private static final Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);

    private static final String REGISTER_TEMPLATE = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n" +
        "        \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
        "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:th=\"http://www.thymeleaf.org\">\n" +
        "<head>\n" +
        "    <meta name=\"viewport\" content=\"width=device-width\"/>\n" +
        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
        "    <title>Registration Email</title>\n" +
        "</head>\n" +
        "<body bgcolor=\"#FFFFFF\">\n" +
        "<table>\n" +
        "    <tr>\n" +
        "        <td></td>\n" +
        "        <td bgcolor=\"#FFFFFF\">\n" +
        "            <div>\n" +
        "                <table>\n" +
        "                    <tr>\n" +
        "                        <td>\n" +
        "                            <h3>Dear customer,</h3>\n" +
        "                            <p>\n" +
        "                                Thank you for registering\n" +
        "                            </p>\n" +
        "                        </td>\n" +
        "                    </tr>\n" +
        "                </table>\n" +
        "            </div>\n" +
        "        </td>\n" +
        "        <td></td>\n" +
        "    </tr>\n" +
        "</table>\n" +
        "</body>\n" +
        "</html>";

    //TODO change on DB store
    @Override
    public Result<String, GetTemplateError> getTemplateByName(final GetTemplateRequest getTemplateRequest)
    {
        final Locale locale = getTemplateRequest.getLocale();
        final String templateName = getTemplateRequest.getTemplateName();

        if (!locale.asString().equalsIgnoreCase("EN"))
        {
            logger.info("Locale not found : templateName={}, locale={}", templateName, locale);
            return Result.failed(GetTemplateError.LOCALE_NOT_FOUND);
        }

        if (!templateName.equalsIgnoreCase("register"))
        {
            logger.info("Template not found : templateName={}, locale={}", templateName, locale);
            return Result.failed(GetTemplateError.TEMPLATE_NOT_FOUND);
        }

        return Result.success(REGISTER_TEMPLATE);
    }
}

package Conversor;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
/**
*
* @author JavaES
*/
@FacesConverter(value = "simpleEntityConverter")
public class SimpleEntityConverter implements Converter 
{
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) 
    {
        if (value != null) 
        {
        return this.getAttributesFrom(component).get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value)
    {
        if (value != null && !"".equals(value)) 
        {
            BaseEntity entity = (BaseEntity) value;
            this.addAttribute(component, entity);
            Long codigo = entity.getId();
            if (codigo != null) 
            {
                return String.valueOf(codigo);
            }
        }
        return (String) value;
    }

    protected void addAttribute(UIComponent component, BaseEntity o) 
    {
        String key = o.getId().toString();
        this.getAttributesFrom(component).put(key, o);
    }
    protected Map<String, Object> getAttributesFrom(UIComponent component)
    {
        return component.getAttributes();
    }
}
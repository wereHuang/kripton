package bind.kripton70;

import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.binder.context.JacksonContext;
import com.abubusoft.kripton.binder.context.XmlBinderContext;
import com.abubusoft.kripton.binder.core.AbstractMapper;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.binder.xml.XMLEventConstants;
import com.abubusoft.kripton.binder.xml.XmlParser;
import com.abubusoft.kripton.binder.xml.XmlSerializer;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is the shared preference binder defined for Bean70A
 *
 * @see Bean70A
 */
@BindMap
public class Bean70ABindMap extends AbstractMapper<Bean70A> {
  /**
   * create new object instance
   */
  @Override
  public Bean70A createInstance() {
    return new Bean70A();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(JacksonContext context, Bean70A object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field valueBean
      if (object.valueBean!=null)  {
        fieldCount++;
        jacksonSerializer.writeFieldName("valueBean");
        context.mapperFor(Bean70A.class).serializeOnJackson(context, object.valueBean, wrapper);
      }

      // field valueString
      if (object.valueString!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueString", object.valueString);
      }

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJacksonAsString(JacksonContext context, Bean70A object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field valueBean
      if (object.valueBean!=null)  {
        fieldCount++;
        jacksonSerializer.writeFieldName("valueBean");
        if (context.mapperFor(Bean70A.class).serializeOnJacksonAsString(context, object.valueBean, wrapper)==0) {
          jacksonSerializer.writeNullField("valueBean");
        }
      }

      // field valueString
      if (object.valueString!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueString", object.valueString);
      }

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(XmlBinderContext context, Bean70A object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("root");
      }

      // Persisted fields:

      // field valueBean
      if (object.valueBean!=null)  {
        xmlSerializer.writeStartElement("valueBean");
        context.mapperFor(Bean70A.class).serializeOnXml(context, object.valueBean, wrapper, 1);
        xmlSerializer.writeEndElement();
      }

      // field valueString
      if (object.valueString!=null) {
        xmlSerializer.writeStartElement("valueString");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueString));
        xmlSerializer.writeEndElement();
      }

      if (currentEventType == 0) {
        xmlSerializer.writeEndElement();
      }
    } catch(Exception e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * create new object instance
   */
  @Override
  public Bean70A parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean70A instance = createInstance();
      String fieldName;
      if (jacksonParser.currentToken() == null) {
        jacksonParser.nextToken();
      }
      if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
        jacksonParser.skipChildren();
        return instance;
      }
      while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
        fieldName = jacksonParser.getCurrentName();
        jacksonParser.nextToken();

        // Parse fields:
        switch (fieldName) {
            case "valueBean":
              // field valueBean
              if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                instance.valueBean=context.mapperFor(Bean70A.class).parseOnJackson(context, wrapper);
              }
            break;
            case "valueString":
              // field valueString
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueString=jacksonParser.getText();
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (IOException e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public Bean70A parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean70A instance = createInstance();
      String fieldName;
      if (jacksonParser.getCurrentToken() == null) {
        jacksonParser.nextToken();
      }
      if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
        jacksonParser.skipChildren();
        return instance;
      }
      while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
        fieldName = jacksonParser.getCurrentName();
        jacksonParser.nextToken();

        // Parse fields:
        switch (fieldName) {
            case "valueBean":
              // field valueBean
              if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
                instance.valueBean=context.mapperFor(Bean70A.class).parseOnJacksonAsString(context, wrapper);
              }
            break;
            case "valueString":
              // field valueString
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueString=jacksonParser.getText();
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (IOException e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public Bean70A parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlParser xmlParser = wrapper.xmlParser;
      Bean70A instance = createInstance();
      int eventType = currentEventType;
      boolean read=true;

      if (currentEventType == 0) {
        eventType = xmlParser.next();
      } else {
        eventType = xmlParser.getEventType();
      }
      String currentTag = xmlParser.getName().toString();
      String elementName = currentTag;
      // No attributes found

      //sub-elements
      while (xmlParser.hasNext() && elementName!=null) {
        if (read) {
          eventType = xmlParser.next();
        } else {
          eventType = xmlParser.getEventType();
        }
        read=true;
        switch(eventType) {
            case XMLEventConstants.START_ELEMENT:
              currentTag = xmlParser.getName().toString();
              switch(currentTag) {
                  case "valueBean":
                    // property valueBean
                    instance.valueBean=context.mapperFor(Bean70A.class).parseOnXml(context, wrapper, eventType);
                  break;
                  case "valueString":
                    // property valueString
                    instance.valueString=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                  break;
                  default:
                    xmlParser.skipElement();
                  break;
                }
              break;
              case XMLEventConstants.END_ELEMENT:
                currentTag = elementName;
                elementName = null;
              break;
              case XMLEventConstants.CDATA:
              case XMLEventConstants.CHARACTERS:
                // no property is binded to VALUE o CDATA break;
              default:
              break;
          }
        }
        return instance;
      } catch(Exception e) {
        e.printStackTrace();
        throw (new KriptonRuntimeException(e));
      }
    }
  }
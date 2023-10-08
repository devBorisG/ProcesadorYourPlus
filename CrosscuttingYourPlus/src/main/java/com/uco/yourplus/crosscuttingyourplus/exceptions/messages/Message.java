package com.uco.yourplus.crosscuttingyourplus.exceptions.messages;

import com.uco.yourplus.crosscuttingyourplus.exceptions.messages.enumeration.MessageLevel;
import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;

public class Message {

    private MessageLevel level;
    private String content;

    /**
     * Constructor predeterminado que crea un mensaje con nivel FATAL y contenido vacío.
     */
    public Message() {
        setLevel(MessageLevel.FATAL);
        setContent(StringHelper.EMPTY);
    }

    /**
     * Constructor que permite crear un mensaje con un nivel y contenido específicos.
     *
     * @param level   El nivel de importancia del mensaje.
     * @param content El contenido del mensaje.
     */
    public Message(MessageLevel level, String content) {
        super();
        setLevel(level);
        setContent(content);
    }

    /**
     * Crea un mensaje con nivel FATAL y un contenido específico.
     *
     * @param content El contenido del mensaje.
     * @return Un objeto Message con nivel FATAL y el contenido proporcionado.
     */
    public static Message createFatalMessage(final String content) {
        return new Message(MessageLevel.FATAL, content);
    }

    /**
     * Crea un mensaje con nivel ERROR y un contenido específico.
     *
     * @param content El contenido del mensaje.
     * @return Un objeto Message con nivel ERROR y el contenido proporcionado.
     */
    public static Message createErrorMessage(final String content) {
        return new Message(MessageLevel.ERROR, content);
    }

    /**
     * Crea un mensaje con nivel WARNING y un contenido específico.
     *
     * @param content El contenido del mensaje.
     * @return Un objeto Message con nivel WARNING y el contenido proporcionado.
     */
    public static Message createWarningMessage(final String content) {
        return new Message(MessageLevel.WARNING, content);
    }

    /**
     * Crea un mensaje con nivel INFO y un contenido específico.
     *
     * @param content El contenido del mensaje.
     * @return Un objeto Message con nivel INFO y el contenido proporcionado.
     */
    public static Message createInfoMessage(final String content) {
        return new Message(MessageLevel.INFO, content);
    }

    /**
     * Crea un mensaje con nivel SUCCESS y un contenido específico.
     *
     * @param content El contenido del mensaje.
     * @return Un objeto Message con nivel SUCCESS y el contenido proporcionado.
     */
    public static Message createSuccesMessage(final String content) {
        return new Message(MessageLevel.SUCCESS, content);
    }

    /**
     * Obtiene la capa del mensaje.
     *
     * @return la capa del mensaje.
     */
    public MessageLevel getLevel() {
        return level;
    }

    /**
     * Establece la capa del mensaje.
     *
     * @param level La capa del mensaje.
     */
    public void setLevel(MessageLevel level) {
        this.level = level;
    }

    /**
     * Obtiene el contenido del mensaje.
     *
     * @return El contenido del mensaje.
     */
    public String getContent() {
        return content;
    }

    /**
     * Establece el contenido del mensaje.
     *
     * @param content El contenido del mensaje.
     */
    public void setContent(String content) {
        this.content = content;
    }
}

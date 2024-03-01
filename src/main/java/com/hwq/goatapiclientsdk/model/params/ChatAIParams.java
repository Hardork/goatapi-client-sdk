package com.hwq.goatapiclientsdk.model.params;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author HWQ
 * @date 2024/2/28 11:51
 * @description
 */
@Data
@Accessors(chain = true)
public class ChatAIParams implements Serializable {
    private static final long serialVersionUID = 3815188540434269370L;

    private String api = "51";

    private String key = "eac190d8b34c0e57443b77b44deed248";

    private String text;
}
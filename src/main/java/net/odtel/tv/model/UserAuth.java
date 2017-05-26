/* ===========================================================================
 * Copyright (c) 2015 Comcast Corp. All rights reserved.
 * ===========================================================================
 *
 * Author: Alexander Ievstratiev
 * Created: 05/26/2017  6:05 PM
 */
package net.odtel.tv.model;

import lombok.Data;

@Data
public class UserAuth {
    private String userName;
    private String password;
}

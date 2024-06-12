package sse.edu.SPR2024.consts;

public class Constant {
    public static final String EMAIL_SUBJECT = "Congrats! You completed a course!";
    public static final String EMAIL_CONTENT = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <style>\n" +
            "        body {\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "            background-color: #f4f4f4;\n" +
            "        }\n" +
            "        .container {\n" +
            "            padding: 20px;\n" +
            "            background-color: #ffffff;\n" +
            "            margin: 20px auto;\n" +
            "            max-width: 600px;\n" +
            "            border-radius: 8px;\n" +
            "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
            "        }\n" +
            "        h1 {\n" +
            "            color: #333333;\n" +
            "        }\n" +
            "        p {\n" +
            "            font-size: 14px;\n" +
            "            line-height: 1.6;\n" +
            "            color: #666666;\n" +
            "        }\n" +
            "        .button {\n" +
            "            display: inline-block;\n" +
            "            padding: 10px 20px;\n" +
            "            margin: 20px 0;\n" +
            "            font-size: 16px;\n" +
            "            color: #ffffff;\n" +
            "            background-color: #28a745;\n" +
            "            text-decoration: none;\n" +
            "            border-radius: 5px;\n" +
            "        }\n" +
            "        .footer {\n" +
            "            text-align: center;\n" +
            "            font-size: 12px;\n" +
            "            color: #999999;\n" +
            "            margin-top: 20px;\n" +
            "        }\n" +
            "        .certificate {\n" +
            "            text-align: center;\n" +
            "            margin: 20px 0;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"container\">\n" +
            "        <h1>Congratulations on Completing Your Course!</h1>\n" +
            "        <p>Dear %s,</p>\n" +
            "        <p>We are thrilled to congratulate you on successfully completing the <strong>%s</strong> course. Your dedication and hard work have paid off, and we are incredibly proud of your accomplishment.</p>\n" +
            "        <p>As a token of our appreciation, please find your certificate attached.</p>\n" +
            "        <p>We hope you enjoyed the course and found it valuable. We look forward to seeing you in our future courses.</p>\n" +
            "        <a href=\"%s\" class=\"button\">View Certificate</a>\n" +
            "        <p>If you have any questions or need further assistance, feel free to contact us at any time.</p>\n" +
            "        <p>Best regards,</p>\n" +
            "        <p>The [GCP Edu] Team</p>\n" +
            "        <div class=\"footer\">\n" +
            "            <p>&copy; 2024 [GCP Edu], Inc. All rights reserved.</p>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>";
}

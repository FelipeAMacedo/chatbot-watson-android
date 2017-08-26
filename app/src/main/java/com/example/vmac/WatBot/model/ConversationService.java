package com.example.vmac.WatBot.model;

/**
 * Created by felipemacedo on 24/08/17.
 */

public class ConversationService extends Service {
    private String workspaceId;

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }
}

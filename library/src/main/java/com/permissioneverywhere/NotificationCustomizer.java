package com.permissioneverywhere;

import android.support.v4.app.NotificationCompat.Builder;

public abstract class NotificationCustomizer {
    abstract public Builder assembly(Builder builder);

    public NotificationCustomizer andThen(final NotificationCustomizer that) {
        final NotificationCustomizer self = this;
        return new NotificationCustomizer() {
            @Override
            public Builder assembly(final Builder builder) {
                return that.assembly(self.assembly(builder));
            }
        };
    }
}

package net.skhu.codingFriends.config.DBReplica;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType dataSourceType = TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? DataSourceType.Slave : DataSourceType.Master;
        return dataSourceType;
    }
}

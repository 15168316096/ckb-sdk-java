package org.nervos.ckb.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by duanyytop on 2018-12-20.
 * Copyright © 2018 Nervos Foundation. All rights reserved.
 */
public class Block {

    public Header header;

    @JsonProperty("commit_transactions")
    public List<Transaction> commitTransactions;

    @JsonProperty("proposal_transactions")
    public Byte[] proposalTransactions;

    public List<UncleBlock> uncles;

}

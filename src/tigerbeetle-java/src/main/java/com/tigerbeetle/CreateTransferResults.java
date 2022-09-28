package com.tigerbeetle;

import java.nio.ByteBuffer;

/**
 * A {@link Batch} of results returned from the {@link Client#createTransfers transfer creation}
 * operation.
 * <p>
 * Successfully executed operations return an empty batch whilst unsuccessful ones return a batch
 * with errors for only the ones that failed. This instance is always ready-only.
 */
public class CreateTransferResults extends Batch {

    interface Struct {

        public static final int Index = 0;
        public static final int Result = 4;

        public final static int SIZE = 8;
    }

    static final CreateTransferResults EMPTY = new CreateTransferResults(0);

    CreateTransferResults(final int capacity) {
        super(capacity, Struct.SIZE);
    }

    CreateTransferResults(ByteBuffer buffer) {
        super(buffer, Struct.SIZE);
    }

    /**
     * Gets the {@link Transfers#getPosition position} of the related transfer in the submitted
     * batch.
     *
     * @return a zero-based index.
     */
    public int getIndex() {
        return getUInt32(at(Struct.Index));
    }

    /**
     * Get the error that occurred during the creation of the transfer
     *
     * @return see {@link CreateTransferResult}.
     */
    public CreateTransferResult getResult() {
        final var value = getUInt32(at(Struct.Result));
        return CreateTransferResult.fromValue(value);
    }
}
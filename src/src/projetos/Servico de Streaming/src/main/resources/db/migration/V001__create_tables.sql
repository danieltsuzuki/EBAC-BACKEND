CREATE TABLE accounts (
                          id UUID PRIMARY KEY NOT NULL,
                          plan_type VARCHAR(30) NOT NULL,
                          subscriber_user_id UUID,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP
);

CREATE TABLE users (
                       id UUID PRIMARY KEY NOT NULL,
                       account_id UUID,
                       user_type VARCHAR(30) NOT NULL,
                       name VARCHAR(100) NOT NULL,
                       age INT NOT NULL,
                       perfil VARCHAR(50) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP,
                       CONSTRAINT fk_users_account FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);

ALTER TABLE accounts
    ADD CONSTRAINT fk_accounts_subscriber_user
        FOREIGN KEY (subscriber_user_id) REFERENCES users(id)
            ON DELETE SET NULL;

CREATE UNIQUE INDEX ux_one_subscriber_per_account
    ON users(account_id)
    WHERE user_type = 'SUBSCRIBER';

CREATE TABLE films (
                        id UUID PRIMARY KEY NOT NULL,
                        title VARCHAR(100) NOT NULL,
                        age_rating INT NOT NULL,
                        genre VARCHAR(50) NOT NULL,
                        plan_type INT NOT NULL,
                        duration INT NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP
);

CREATE TABLE watch_history (
                               id UUID PRIMARY KEY NOT NULL,
                               user_id UUID NOT NULL,
                               film_id UUID NOT NULL,
                               watched_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES users(id),
                               FOREIGN KEY (film_id) REFERENCES films(id)
);


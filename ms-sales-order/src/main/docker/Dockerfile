FROM postgres:14.2

RUN echo PGOPTS=-i >> /var/lib/postgresql/data/postgresql.conf
RUN echo "host    all    all    0.0.0.0/0    md5" >> /var/lib/postgresql/data/pg_hba.conf

CMD ["postgres"]

package org.example;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Router2 {



        String Port;
        String Name ;
        String Time;
        String Date;
        String IpAdd;
        String SubnetMask;
        Timestamp expiration;



    }



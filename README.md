# God(e) core
The Java-Maven core for God(e) code generator

## What?

The god(e) core is a set of abstractions for God(e) code generator (https://github.com/manugraj/code-generator) to work. This repo is the Java-Maven implementation of the same. We are working on releasing flask based Python implementation as well.

## Why?

We do not want to generate the entire code for the user. The user can ask for a set of configuration and we would dynamically extend or implement abstractions in this repo to enable the configuration.

## How?

We use standard java extension and implementation stratergies along with spring bean discovery to provide the features. We are directly not using java reflections, but we do use spring to configure dependencies.

## Components
- Store => For database support. Currently we support JPA store and MongoDB
- Cache => For caching support. Currently we support ignite caching system.
- Media => A media server to enable encrypted file storage system.
- Queue => A queueing system based on Apache Kafka
- Auth => Authentication system based on oauth2

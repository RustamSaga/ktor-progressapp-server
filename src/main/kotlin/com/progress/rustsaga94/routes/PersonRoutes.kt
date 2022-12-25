package com.progress.rustsaga94.routes

import com.fasterxml.jackson.core.format.DataFormatDetector
import com.mongodb.internal.connection.Time
import com.progress.rustsaga94.data.PersonDataSource
import com.progress.rustsaga94.data.UserDataSource
import com.progress.rustsaga94.data.models.Person
import com.progress.rustsaga94.data.models.User
import com.progress.rustsaga94.data.models.requests.AuthRequest
import com.progress.rustsaga94.data.models.requests.PersonRequest
import com.progress.rustsaga94.security.hashing.HashingService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.netty.handler.codec.DateFormatter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

// TODO (getPerson, putPerson, deletePerson, getPersonByDetails...)
fun Route.getPerson(){
    authenticate{
        get ("getPerson") {
            val principal = call.principal<JWTPrincipal>()
            val person = principal?.getClaim("person", Person::class)
            call.respond(HttpStatusCode.OK, person.toString())
        }
    }

}
//
//fun Route.putPerson(
//    hashingService: HashingService,
//    personDataSource: PersonDataSource,
//    userDataSource: UserDataSource
//){
//    authenticate {
//        post("putPerson") {
//            val request = call.receiveOrNull<PersonRequest>() ?: kotlin.run {
//                call.respond(HttpStatusCode.BadRequest)
//                return@post
//            }
//
//            val isNameUserBlank = request.nameUser.isBlank()
//            val isPersonIdBlank = request.personId.isBlank()
//
//            when {
//                isNameUserBlank -> {
//                    call.respond(HttpStatusCode.Conflict, "User name is blank")
//                }
//                isPersonIdBlank -> {
//                    call.respond(HttpStatusCode.Conflict, "Person id is blank")
//                }
//            }
//
//            val dateTime = LocalDateTime.now()
//                .format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a"))
//            val person = Person(
//                userName = request.nameUser,
//                name = request.name,
//                joined_data = dateTime,
//            )
//            val wasAcknowledged = personDataSource.putPerson(Person)
//            if (!wasAcknowledged) {
//                call.respond(HttpStatusCode.Conflict)
//                return@post
//            } else {
//                call.respond(HttpStatusCode.OK)
//            }
//
//        }
//    }
//}
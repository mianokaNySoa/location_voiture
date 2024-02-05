package cloud.voiture.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cloud.voiture.authentification.JwtUtil;
import cloud.voiture.model.Utilisateur;
import cloud.voiture.model.request.LoginReq;
import cloud.voiture.model.response.ErrorRes;
import cloud.voiture.model.response.LoginRes;
import cloud.voiture.repository.UtilisateurRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/rest/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
    private final AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    private final UtilisateurRepository utilisateurRepository;

    public LoginController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,UtilisateurRepository utilisateurRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginReq loginReq) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(),
                            loginReq.getPassword()));
            String email = authentication.getName();
            
            // Utilisateur user = new Utilisateur(email, "");

            Utilisateur user = utilisateurRepository.findByEmail(email);
            String role = ((List<? extends GrantedAuthority>) authentication.getAuthorities()).get(0).getAuthority();
            // user.setRole(role);
            // System.out.println(user.getId());
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(email, token);

            return ResponseEntity.ok(loginRes);
        } catch (BadCredentialsException e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(null);
    }

    @GetMapping("/user-info")
    public ResponseEntity<String> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Accéder au nom de l'utilisateur
        String username = authentication.getName();

        // Accéder aux rôles de l'utilisateur
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();

        // Votre logique métier
        return ResponseEntity.ok("Username: " + username + ", Roles: " + authorities);
    }
}

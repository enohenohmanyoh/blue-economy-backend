package com.blue_economy.service;

import com.blue_economy.dto.ChatRequest;

import com.blue_economy.model.Chat;

import com.blue_economy.repository.ChatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    public ChatService(ChatRepository messageRepository) {
        this.chatRepository = messageRepository;
    }

    public List<Chat> getAll() {
        return chatRepository.findAll()
                .stream()
                .sorted((a, b) -> a.getId().compareTo(b.getId()))
                .toList();
    }

    @Transactional
    public Chat saveUserMessage(ChatRequest req) {
        Chat saved = chatRepository.save(
                Chat .builder()
                        .text(req.text())
                        .sender(req.sender())
                        .build()
        );

        // Bot reply logic (predefined)
        if ("user".equalsIgnoreCase(req.sender())) {
            String reply = getBotReply(req.text());
            chatRepository.save(
                    Chat.builder()
                            .text(reply)
                            .sender("bot")
                            .build()
            );
        }

        return saved;
    }

    private String getBotReply(String userInput) {
        String msg = userInput.toLowerCase();

        if (msg.contains("hi") || msg.contains("hello")) {
            return "Hello 👋 Welcome to the Pretoria Training Academy Assistant! We provide workshops, vocational training, conferences, and blue economy courses. How can I assist you today?";
        }
        else if (msg.contains("blue economy") || msg.contains("economic growth") || msg.contains("conservancy")) {
            return "Here are our Blue Economy courses:\n" +
                    "ES001: Unpacking the ultimate and peculiar purpose of blue economy development (5 Days)\n" +
                    "ES002: Blue economy resilience and climate change mitigation and adaptation (5 Days)\n" +
                    "ES003: Maritime Spatial Planning (5 Days)\n" +
                    "ES004: Climate finance for blue economy development (5 Days)\n" +
                    "ES005: Maritime environmental management systems (5 Days)\n" +
                    "ES007: Sustainability leadership for blue economy development (5 Days)\n" +
                    "ES008: Sustainability marketing for blue economy development (5 Days)";
        }
        else if (msg.contains("training and capacity") || msg.contains("TCB")) {
            return "Our Training and Capacity Building courses include:\n" +
                    "TCB001: Blue economy development strategy (5 Days)\n" +
                    "TCB002: Blue economy development strategy reviews (5 Days)\n" +
                    "TCB003: Blue Finance (blue economy development financing models) (5 Days)\n" +
                    "TCB004: Fisheries management and aquaculture value chain (5 Days)\n" +
                    "TCB005: Shipping and maritime transport management (5 Days)\n" +
                    "TCB006: Oil and gas (5 Days)\n" +
                    "TCB007: Marine renewable energy (5 Days)\n" +
                    "TCB007: Blue product development (5 Days)\n" +
                    "TCB008: Coastal and marine tourism (5 Days)";
        }
        else if (msg.contains("policy") || msg.contains("governance") || msg.contains("PGS")) {
            return "Our Policy and Governance Support courses include:\n" +
                    "PGS001: Maximising international maritime law for blue economy development (5 Days)\n" +
                    "PGS002: Policy design and development (5 Days)\n" +
                    "PGS003: Policy analysis and impact assessment (5 Days)\n" +
                    "PGS004: Maritime safety, security and compliance (5 Days)\n" +
                    "PGS005: Maritime cyber security (5 Days)\n" +
                    "PGS006: Maritime safety and security (5 Days)\n" +
                    "PGS007: Advisory services for national and regional bodies (5 Days)\n" +
                    "PGS008: Maritime stakeholder management and governance frameworks (5 Days)";
        }
        else if (msg.contains("partnership") || msg.contains("regional") || msg.contains("international") || msg.contains("P")) {
            return "Our Partnerships, Regional & International Cooperation courses include:\n" +
                    "P001: Multi-stakeholder partnerships for blue economy development (5 Days)\n" +
                    "P002: Leadership in multi-stakeholder Partnership for blue economy development (5 Days)\n" +
                    "P003: Maritime (blue economy) diplomacy (5 Days)\n" +
                    "P004: Co-design and co-delivery with partners (5 Days)\n" +
                    "P005: Exchange programs (5 Days)\n" +
                    "P006: Joint projects with regional bodies (5 Days)";
        }
        else if (msg.contains("book") || msg.contains("register") || msg.contains("signup")) {
            return "Awesome! 🎉 Please provide your name, email, and the course you would like to register for so we can complete your booking.";
        }
        else if (msg.contains("price") || msg.contains("cost") || msg.contains("fee")) {
            return "Our course fees range depending on the program, typically from R1,500 to R8,000. Group discounts and scholarships may be available!";
        }
        else if (msg.contains("schedule") || msg.contains("dates")) {
            return "Upcoming sessions are available throughout the year. Please specify a course for detailed schedule information.";
        }
        else if (msg.contains("certificate") || msg.contains("certification")) {
            return "Yes! All our courses provide certificates of completion upon successful attendance. 📜";
        }
        else if (msg.contains("location") || msg.contains("address")) {
            return "We are based in Pretoria, South Africa. Exact venue details are shared upon registration.";
        }
        else if (msg.contains("thanks") || msg.contains("thank you")) {
            return "You're welcome! 🙌 Feel free to ask about our courses, workshops, registration, or schedules.";
        }
        else if (msg.contains("help")) {
            return "I can assist you with:\n- Listing all available courses\n- Booking a course\n- Providing course schedules\n- Information about fees and certificates\n- General enquiries about Pretoria Training Academy";
        }

        return "Sorry, I didn't quite get that 🤔. You can ask about our courses, workshops, booking, pricing, schedule, or certificates.";

    }

    public void delete(Long id) {
        chatRepository.deleteById(id);
    }

    public void deleteAll() {
        chatRepository.deleteAll();
    }
}

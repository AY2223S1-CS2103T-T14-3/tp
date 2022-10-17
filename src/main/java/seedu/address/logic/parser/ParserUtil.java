package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.model.record.Medication;
import seedu.address.model.record.Record;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }


    /**
     * Parses a {@code String recordDate} and {@code String recordData} into a {@code Record}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given inputs is invalid.
     */

    /*
    public static Record parseRecord(String recordDate, String recordData) throws ParseException {
        requireAllNonNull(recordDate, recordData);
        String trimmedDate = recordDate.trim();
        String trimmedData = recordData.trim();

        if (!Record.isValidDate(trimmedDate)) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATE_FORMAT);
        }

        if (!Record.isValidRecordData(trimmedData)) {
            throw new ParseException(Messages.MESSAGE_INVALID_RECORD_DATA_FORMAT);
        }

        return new Record(trimmedDate, trimmedData, );
    }

    */

    /**
     * Parses a {@code String recordData} into a {@String recordData}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given inputs is invalid.
     */
    public static String parseRecordData(String recordData) throws ParseException {
        requireNonNull(recordData);
        String trimmedData = recordData.trim();
        if (!Record.isValidRecordData(trimmedData)) {
            throw new ParseException(Messages.MESSAGE_INVALID_RECORD_DATA_FORMAT);
        }
        return trimmedData;
    }

    /**
     * Parses a {@code String recordDate} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given inputs is invalid.
     */
    public static LocalDateTime parseDate(String recordDate) throws ParseException {
        requireNonNull(recordDate);
        String trimmedDate = recordDate.trim();
        if (!Record.isValidDate(trimmedDate)) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATE_FORMAT);
        }
        return LocalDateTime.parse(trimmedDate, Record.DATE_FORMAT);
    }

    /**
     * Parses a {@code String medication} into a {@code Medication}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code medication} is invalid.
     */
    public static Medication parseMedication(String medication) throws ParseException {
        requireNonNull(medication);
        String trimmedMedication = medication.trim();
        if (!Medication.isValidMedication(trimmedMedication)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return Medication.of(trimmedMedication);
    }

    /**
     * Parses {@code Collection<String> medications} into a {@code Set<Medication>}.
     */
    public static Set<Medication> parseMedications(Collection<String> medications) throws ParseException {
        requireNonNull(medications);
        final Set<Medication> medicationSet = new HashSet<>();
        for (String medicationName : medications) {
            medicationSet.add(parseMedication(medicationName));
        }

        // Default case
        if (medicationSet.isEmpty()) {
            medicationSet.add(Medication.of(Medication.MESSAGE_NO_MEDICATION_GIVEN));
        }

        return medicationSet;
    }
}

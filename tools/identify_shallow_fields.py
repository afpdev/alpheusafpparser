import os
import re

# Static mapping for package descriptions
PACKAGE_DESCRIPTIONS = {
    "base": "Internal base classes and common structured field implementations for the Alpheus AFP Parser.",
    "bcoca": "Bar Code Object Content Architecture - Provides the structures for bar code data objects.",
    "cmoca": "Color Management Object Content Architecture - Provides the structures for color management resources (CMRs).",
    "foca": "Font Object Content Architecture - Defines resources for fonts, character sets, and code pages.",
    "goca": "Graphics Object Content Architecture - Provides the structures for vector graphics objects.",
    "lineData": "Definitions for line-printer data maps, page maps, and transmission subcases within the AFP environment.",
    "modca": "Mixed Object Document Content Architecture - The primary data stream architecture for AFP documents, defining the document's structure and component objects.",
    "modca_L": "MO:DCA Color Attribute Table (CAT) extensions, typically used for legacy or specific color-to-grayscale mapping.",
    "ptoca": "Presentation Text Object Content Architecture - Defines the structures for presentation text objects."
}

# Static mapping for field descriptions
FIELD_DESCRIPTIONS = {
    "StructuredFieldErrornouslyBuilt": "Represents a structured field that was built incorrectly due to parsing errors.",
    "BBC_BeginBarCodeObject": "Names and begins a bar code data object.",
    "EBC_EndBarCodeObject": "Terminates a bar code data object.",
    "CMR_ColorManagementResource": "Carries a color management resource (CMR).",
    "BCF_BeginCodedFont": "Names and begins a coded font object.",
    "BCP_BeginCodePage": "Names and begins a code page object.",
    "BFN_BeginFont": "Names and begins a font character set object.",
    "ECF_EndCodedFont": "Terminates a coded font object.",
    "ECP_EndCodePage": "Terminates a code page object.",
    "EFN_EndFont": "Terminates a font character set object.",
    "FNG_FontPatterns": "Carries the character pattern data for a font character set.",
    "FNN_FontNameMap": "Maps font names to global identifiers.",
    "BGR_BeginGraphicsObject": "Names and begins a graphics data object.",
    "EGR_EndGraphicsObject": "Terminates a graphics data object.",
    "GAD_GraphicsData": "Carries the drawing orders that define a graphics object.",
    "BDX_BeginDataMapTransmitionSubcase": "Names and begins a data map transmission subcase.",
    "BPM_BeginPageMap": "Names and begins a page map object.",
    "DXD_DataMapTransmitionSubcaseDescriptor": "Specifies the characteristics of a data map transmission subcase.",
    "EDM_EndDataMap": "Terminates a data map object.",
    "EDX_EndDataMapTransmitionSubcase": "Terminates a data map transmission subcase.",
    "EPM_EndPageMap": "Terminates a page map object.",
    "IDM_InvokeDataMap": "Identifies the data map to be used for formatting.",
    "BAG_BeginActiveEnvironmentGroup": "Names and begins an Active Environment Group (AEG).",
    "BDG_BeginDocumentEnvironmentGroup": "Names and begins a Document Environment Group (DEG).",
    "BDI_BeginDocumentIndex": "Names and begins a document index.",
    "BFG_BeginFormEnvironmentGroup": "Names and begins a Form Environment Group (FEG).",
    "BFM_BeginFormMap": "Names and begins a form map object.",
    "BII_BeginIMImageObject": "Names and begins an IM image object.",
    "BIM_BeginImageObject": "Names and begins an image object.",
    "BMM_BeginMediumMap": "Names and begins a medium map object.",
    "BMO_BeginOverlay": "Names and begins an overlay object.",
    "BNG_BeginNamedPageGroup": "Names and begins a named page group.",
    "BOC_BeginObjectContainer": "Names and begins an object container.",
    "BOG_BeginObjectEnvironmentGroup": "Names and begins an Object Environment Group (OEG).",
    "BPF_BeginPrintFile": "Names and begins a print file.",
    "BPG_BeginPage": "Names and begins a presentation page.",
    "BPS_BeginPageSegment": "Names and begins a page segment object.",
    "BRG_BeginResourceGroup": "Names and begins a resource group.",
    "BSG_BeginResourceEnvironmentGroup": "Names and begins a resource environment group.",
    "EAG_EndActiveEnvironmentGroup": "Terminates an Active Environment Group.",
    "EDG_EndDocumentEnvironmentGroup": "Terminates a Document Environment Group.",
    "EDI_EndDocumentIndex": "Terminates a document index.",
    "EDT_EndDocument": "Terminates a document.",
    "EFG_EndFormEnvironmentGroup": "Terminates a Form Environment Group.",
    "EFM_EndFormMap": "Terminates a form map object.",
    "EII_EndIMImageObject": "Terminates an IM image object.",
    "EIM_EndImageObject": "Terminates an image object.",
    "EMM_EndMediumMap": "Terminates a medium map object.",
    "EMO_EndOverlay": "Terminates an overlay object.",
    "ENG_EndNamedPageGroup": "Terminates a named page group.",
    "EOC_EndObjectContainer": "Terminates an object container.",
    "EOG_EndObjectEnvironmentGroup": "Terminates an Object Environment Group.",
    "EPF_EndPrintFile": "Terminates a print file.",
    "EPG_EndPage": "Terminates a presentation page.",
    "EPS_EndPageSegment": "Terminates a page segment object.",
    "ERG_EndResourceGroup": "Terminates a resource group.",
    "ERS_EndResource": "Terminates a resource object.",
    "ESG_EndResourceEnvironmentGroup": "Terminates a resource environment group.",
    "IEL_IndexElement": "Identifies a document element and its location within the document.",
    "IMM_InvokeMediumMap": "Identifies the medium map to be used for formatting.",
    "IRD_IMImageRasterData": "Carries the raster pattern for an IM image.",
    "NOP_NoOperation": "Carries unarchitected data and has no architectural effect.",
    "OBD_ObjectAreaDescriptor": "Specifies the size and resolution of an object's presentation space.",
    "OCD_ObjectContainerData": "Carries the data for an object container.",
    "PEC_PresentationEnvironmentControl": "Specifies presentation-fidelity requirements and rendering intent.",
    "TLE_TagLogicalElement": "Assigns an attribute name and value to a document component.",
    "BCA_BeginColorAttributeTable": "Names and begins a color attribute table.",
    "CAT_ColorAttributeTable": "Carries the color-to-grayscale mapping definitions.",
    "ECA_EndColorAttributeTable": "Terminates a color attribute table.",
    "BPT_BeginPresentationTextObject": "Names and begins a presentation text object.",
    "EPT_EndPresentationTextObject": "Terminates a presentation text object."
}

def identify_shallow_fields(root_dir):
    shallow_fields = {}

    # regex to find class definition and base classes
    class_regex = re.compile(r'public class (\w+) extends (\w+)')
    # regex to find decodeAFP method
    decode_regex = re.compile(r'public void decodeAFP\s*\(')

    for root, dirs, files in os.walk(root_dir):
        for file in files:
            if file.endswith('.java'):
                filepath = os.path.join(root, file)
                with open(filepath, 'r') as f:
                    content = f.read()

                    # Skip non-StructuredField classes
                    if 'extends StructuredField' not in content and 'extends StructuredFieldBase' not in content:
                        continue

                    match = class_regex.search(content)
                    if match:
                        classname = match.group(1)
                        # Check if it overrides decodeAFP
                        if not decode_regex.search(content):
                            # Special case: classes extending StructuredFieldBaseNameAndTriplets
                            # or StructuredFieldBaseTriplets are often considered "full" for simple fields.
                            # Also classes extending StructuredFieldBaseData if they are just data containers.
                            if match.group(2) in ['StructuredFieldBaseNameAndTriplets', 'StructuredFieldBaseTriplets', 'StructuredFieldBaseName']:
                                continue

                            if classname in ['IRD_IMImageRasterData', 'OCD_ObjectContainerData', 'FNG_FontPatterns', 'NOP_NoOperation', 'StructuredFieldErrornouslyBuilt']:
                                continue

                            package = os.path.relpath(root, root_dir).replace(os.sep, '.')
                            if package not in shallow_fields:
                                shallow_fields[package] = []
                            shallow_fields[package].append(classname)
                        else:
                            # Also check for minimal/empty implementations (e.g., GAD_GraphicsData)
                            decode_body_match = re.search(r'public void decodeAFP\s*\([^)]*\)\s*throws\s*AFPParserException\s*\{([\s\S]*?)\}', content)
                            if decode_body_match:
                                body = decode_body_match.group(1).strip()
                                if not body or body == 'super.decodeAFP(sfData, offset, length, config);' or body.startswith('//'):
                                     package = os.path.relpath(root, root_dir).replace(os.sep, '.')
                                     if package not in shallow_fields:
                                         shallow_fields[package] = []
                                     shallow_fields[package].append(classname + " (Minimal/Empty override)")

    return shallow_fields

def generate_report(shallow_fields, output_file):
    with open(output_file, 'w') as f:
        f.write("# Shallow Structured Field Implementations Report\n\n")
        f.write("This report lists all Structured Field classes that do not provide their own payload parsing logic (shallow implementations).\n\n")

        total_count = sum(len(fields) for fields in shallow_fields.values())
        f.write(f"**Total Shallow Fields Identified: {total_count}**\n\n")

        for package in sorted(shallow_fields.keys()):
            f.write(f"## Package: {package}\n")
            desc = PACKAGE_DESCRIPTIONS.get(package, "")
            if desc:
                f.write(f"*{desc}*\n")

            for field in sorted(shallow_fields[package]):
                # Extract clean field name for description lookup
                clean_field = field.split(" ")[0]
                field_desc = FIELD_DESCRIPTIONS.get(clean_field, "")
                if field_desc:
                    f.write(f"- {field}: {field_desc}\n")
                else:
                    f.write(f"- {field}\n")
            f.write("\n")

if __name__ == "__main__":
    src_root = "src/main/java/com/mgz/afp"
    report_file = "SHALLOW_FIELDS_REPORT.md"
    fields = identify_shallow_fields(src_root)
    generate_report(fields, report_file)
    print(f"Report generated: {report_file}")

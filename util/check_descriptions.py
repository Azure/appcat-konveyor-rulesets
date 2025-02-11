"""
This script takes directory with yaml rulesets and checks if there are rules without/with emtpy description field.

Usage:
   check_descriptions.py -d <rules_dir> [-o <output_dir>] [-r] [-w]

Options:
   -h --help                     Show this screen.
   -d, --directory=<rules_dir>   Directory with the rulesets.
   -w, --overwrite               Overwrite the original rulesets.
   -o, --output=<output_dir>     Directory to save the new rulesets if -w is not set.
   -r, --recursive               Recursively check all subdirectories.
"""

from pathlib import Path
import yaml
import docopt


def analyze(directory, overwrite=False, recursive=False, output_dir=None):
   checked_cnt = 0
   missing_cnt = 0
   rel_output_dir = Path(output_dir) / Path(directory).relative_to(Path(directory).anchor) if output_dir else None

   print("#######################################################################")
   print("Analyzing %s" % directory)
   print("Output directory: %s" % (rel_output_dir if rel_output_dir else "None"))
   print

   if rel_output_dir:
      Path(rel_output_dir).mkdir(parents=True, exist_ok=True, mode=0o755)

   rulesets_output = {}
   for f in directory.iterdir():
      if f.is_file() and f.suffix == '.yaml' and not f.name.endswith('ruleset.yaml'):
         rulesets_output[f] = None
         if overwrite:
            rulesets_output[f] = f
         elif rel_output_dir:
            rulesets_output[f] = rel_output_dir / f.name

   for ruleset, ruleset_out in rulesets_output.items():
      rules = {}
      ruleset_missing_cnt = 0
      updated_ruleset = False

      with open(ruleset) as data:
         try:
            rules = yaml.safe_load(data)
            for rule in rules:
               checked_cnt += 1
               if rule.get("description") == None or rule["description"] == "":
                  ruleset_missing_cnt += 1
                  print("  " + rule["ruleID"])
                  # Guess based on when condition
                  if rule.get("when"):
                     tags = rule.get("when").get("builtin.hasTags")   # Guess by Condition Tag
                     if tags and len(tags) == 1:
                        print("    description might be: %s" % tags[0])
                        rule['description'] = tags[0]
                        updated_ruleset = True
                     else:
                        tag = rule.get("tag")  # Guess by (given) Tag
                        if tag and len(tag) == 1:
                           print("    description might be: %s" % tag[0])
                           rule['description'] = tag[0]
                           updated_ruleset = True

         except Exception as exc:
            print("  Error reading %s: %s" % (f, exc))

      if ruleset_out:
         if ruleset_missing_cnt > 0 and updated_ruleset:
            with open(ruleset_out, 'w') as rulesfile:
               print("  Saving updated ruleset to %s" % ruleset_out)
               yaml.dump(rules, rulesfile)
         elif not overwrite:
            with open(ruleset_out, 'w') as rulesfile:
               with open(ruleset, "r", encoding="utf-8") as data:
                  rulesfile.write(data.read())

      missing_cnt += ruleset_missing_cnt

   if recursive:
      for d in Path(directory).iterdir():
         if d.is_dir():
            checked, missing = analyze(d, overwrite, recursive, output_dir)
            checked_cnt += checked
            missing_cnt += missing

   return checked_cnt, missing_cnt


args = docopt.docopt(__doc__)

directory = args['--directory']
output_dir = args['--output']
recursive = args['--recursive']
overwrite = args['--overwrite']

if not Path(directory).is_dir():
   print("Directory not found: %s" % directory)
   exit(1)


checked, missing = analyze(Path(directory), overwrite, recursive, Path(output_dir) if output_dir else None)
print("Checked %d rules, %d missing descriptions" % (checked, missing))
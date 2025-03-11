package com.example.lsp4;

import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.xbase.lib.ListExtensions

class TaskDslGenerator implements IGenerator {

    override void doGenerate(XtextResource resource, IFileSystemAccess fsa) {
        // Get the model (TaskDsl Model) from the resource
        val model = resource.contents.head as Model

        // The output file path where the generated code will be saved
        val taskFile = fsa.getPath('output/Tasks.java')
        
        // Start building the generated Java file
        val builder = new StringBuilder()
        
        // Write the class header
        builder.append('public class Tasks {\n')
        
        // Loop over all the tasks in the model and generate corresponding code
        model.tasks.forEach [ task |
            builder.append('  public static final String taskName = "')
            builder.append(task.name)
            builder.append('";\n')
            builder.append('  public static final String description = "')
            builder.append(task.description.value)
            builder.append('";\n')
            builder.append('  public static final String priority = "')
            builder.append(task.priority.value)
            builder.append('";\n')
            builder.append('  public static final String dueDate = "')
            builder.append(task.due.date)
            builder.append('";\n\n')
        ]
        
        // Close the class definition
        builder.append('}')
        
        // Generate the file with the constructed content
        fsa.generateFile(taskFile, builder.toString())
    }
}
